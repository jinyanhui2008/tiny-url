package net.qdjinxin.tinyurl;

import lombok.extern.log4j.Log4j2;
import net.qdjinxin.tinyurl.utils.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author 青岛金鑫
 */
@Log4j2
@SpringBootApplication
public class TinyUrlApplication implements CommandLineRunner {
    /**
     * 获取分布式的服务器地址
     */
    @Value("${tiny.server}")
    private String[] service_url;

    public static void main(String[] args) {
        SpringApplication.run(TinyUrlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final InetAddress local = getLocalHostExactAddress();
        String ip = local.getHostAddress();
        for (int i = 0; i < service_url.length; i++) {
            if (service_url[i].contains(ip)) {
                IDUtils.url = i;
            }
        }
    }

    public static InetAddress getLocalHostExactAddress() {
        InetAddress candidateAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface iface = networkInterfaces.nextElement();
                // 该网卡接口下的ip会有多个，也需要一个个的遍历，找到自己所需要的
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddrs.nextElement();
                    // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了 就是我们要找的
                            // ~~~~~~~~~~~~~绝大部分情况下都会在此处返回你的ip地址值~~~~~~~~~~~~~
                            return inetAddr;
                        }
                        // 若不是site-local地址 那就记录下该地址当作候选
                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress == null) {
                candidateAddress = InetAddress.getLocalHost();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 如果出去loopback回环地之外无其它地址了，那就回退到原始方案吧
        return candidateAddress;
    }
}
