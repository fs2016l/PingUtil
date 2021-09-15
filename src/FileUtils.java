import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luqiwei
 * @version 1.0
 * @date 2021/9/15 13:18
 */
public class FileUtils {

    /**
     * 按行读取文件
     * @param filePath 文件路径
     * @return 按行读取的list
     */
    public static List<String> readFile(String filePath) {
        java.util.List<String> ipList = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        String temp = null;
        int line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                temp = temp.replaceAll("\\s*", "");
                ipList.add(temp);
//                System.out.println("line" + line + ":" + temp);
                line++;
            }
            return ipList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
