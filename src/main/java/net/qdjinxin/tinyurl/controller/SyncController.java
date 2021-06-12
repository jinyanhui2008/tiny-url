package net.qdjinxin.tinyurl.controller;

import net.qdjinxin.tinyurl.dto.TinyURLDTO;
import net.qdjinxin.tinyurl.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @author 金鑫
 * @title: SyncController
 * @projectName tiny-url
 * @description: 同步服务
 * @date 2021/6/12 09:56
 */
@RestController
@RequestMapping("/api/sync")
public class SyncController {
    @Value("${tiny.url}")
    private String tinyUrl;

    /**
     * 创建短网址文件
     *
     * @param url
     * @param id
     */
    @PostMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(String url, String id) {
        final String pathById = FileUtils.createPathById(id);
        final String savePath = FileUtils.createSavePath(pathById);
        FileUtils.saveHtml(savePath, url);
        return this.tinyUrl + File.pathSeparator + pathById;
    }
}
