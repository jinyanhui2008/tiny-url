package net.qdjinxin.tinyurl.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * @author 金鑫
 * @title: FileUtils
 * @projectName tiny-url
 * @description: 文件服务
 * @date 2021/6/12 09:53
 */
@Log4j2
@Component
public class FileUtils {
    private static String filePath;

    @Value("${tiny.file.path}")
    public void setFilePath(String filePath) {
        FileUtils.filePath = filePath;
    }

    /**
     * 创建保存路径的物理路径
     *
     * @param path
     * @return
     */
    public static String createSavePath(String path) {
        final String savePath = filePath + File.separator + path;
        return savePath;
    }

    /**
     * 根据id创建文件路径
     *
     * @param id
     * @return
     */
    public static String createPathById(String id) {
        String path1 = id.substring(0, 2);
        String path2 = id.substring(2, 4);
        String path3 = id.substring(4);
        return path1 + File.separator + path2 + File.separator + path3;
    }

    /**
     * 保存短网址跳转文件
     *
     * @param path 保存路径
     * @param url 跳转链接
     */
    public static void saveHtml(String path, String url) {
        String html = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>正在跳转</title>" +
                "    <meta http-equiv=\"refresh\" content=\"1;url=" + url + "\">" +
                "</head>" +
                "<body>" +
                "<script>" +
                "    window.location.href = \"" + url + "\";" +
                "</script>" +
                "</body>" +
                "</html>";
        File fileDir = new File(path);
        final boolean mkdirs = fileDir.mkdirs();
        if(!mkdirs){
            return;
        }
        File file = new File(path + File.separator + "index.html");
        try (OutputStream fos = new FileOutputStream(file); Writer write = new OutputStreamWriter(fos, Charset.defaultCharset()); BufferedWriter bufferedWriter = new BufferedWriter(write)) {
            bufferedWriter.write(html);
            bufferedWriter.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
