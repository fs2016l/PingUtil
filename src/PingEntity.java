import lombok.Data;

import java.util.List;

/**
 * @author luqiwei
 * @version 1.0
 * @date 2021/9/15 13:44
 */

public class PingEntity {
    /**
     * IP地址
     */
    private String ip;
    /**
     * 状态
     */
    private boolean state;
    /**
     * 延迟
     */
    private String delay;
    /**
     * 扩展
     */
    private List<String> ext;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public List<String> getExt() {
        return ext;
    }

    public void setExt(List<String> ext) {
        this.ext = ext;
    }
}
