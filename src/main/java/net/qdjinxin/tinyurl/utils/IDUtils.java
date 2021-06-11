package net.qdjinxin.tinyurl.utils;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.util.Stack;

/**
 * @author 金鑫
 * @title: IDUtils
 * @projectName tiny-url
 * @description: 短网址生成id服务
 * @date 2021/6/11 22:06
 */
@Log4j2
public class IDUtils {
    private static final char[] CHAR_ARRAY = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz".toCharArray();
    /**
     * 这是创建url生成的第一个参数
     */
    public static int url = -1;

    /**
     * 根据当前时间创建一个按照毫秒数全局唯一ID,理论上每秒最多可以创建1000个id
     *
     * @return
     */
    @SneakyThrows
    public synchronized static String createId() {
        if (url < 0) {
            log.error("未获取系统唯一ID,无法创建ID,系统退出");
            System.exit(0);
        }
        Thread.sleep(1);
        BigInteger rest = new BigInteger(String.valueOf(System.currentTimeMillis()));
        BigInteger big1 = new BigInteger("1");
        BigInteger big58 = new BigInteger("58");
        // 创建栈
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (rest.compareTo(big1) >= 1) {
            // 进栈,
            BigInteger d = rest.mod(big58);
            int i = d.intValue();
            stack.add(CHAR_ARRAY[i]);
            rest = rest.divide(big58);
        }
        for (; !stack.isEmpty(); ) {
            // 出栈
            result.append(stack.pop());
        }
        return CHAR_ARRAY[url] + result.toString();
    }

    public static void main(String[] args) {
        url = 1;
        log.info(createId());
    }
}
