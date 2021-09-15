import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luqiwei
 * @version 1.0
 * @date 2021/9/15 13:39
 */
public class PingUtils {
    public static PingEntity ping1(String ipAddress) {
        PingEntity pingEntity = new PingEntity();
        pingEntity.setIp(ipAddress);
        try {
            int timeOut = 3000;  //超时应该在3钞以上
            boolean status = false;     // 当返回值是true时，说明host是可用的，false则不可。
            status = InetAddress.getByName(ipAddress).isReachable(timeOut);
            pingEntity.setState(status);
            return pingEntity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PingEntity ping2(String ipAddress) throws Exception {
        PingEntity pingEntity = new PingEntity();
        pingEntity.setIp(ipAddress);
        List<String> lineString = new ArrayList<>();
        String line = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ipAddress);

            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream(), Charset.forName("GBK")));
            while ((line = buf.readLine()) != null) {
                lineString.add(line);
            }
            pingEntity.setExt(lineString);
            return pingEntity;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static PingEntity ping3(String ipAddress, int pingTimes, int timeOut) {
        PingEntity pingEntity = new PingEntity();
        pingEntity.setIp(ipAddress);
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        try {   // 执行命令并获取输出
            System.out.println(pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                pingEntity.setState(false);
                return pingEntity;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();   // 出现异常则返回假
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

}
