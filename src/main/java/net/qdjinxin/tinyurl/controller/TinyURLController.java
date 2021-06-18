package net.qdjinxin.tinyurl.controller;

import net.qdjinxin.tinyurl.dto.TinyURLDTO;
import net.qdjinxin.tinyurl.utils.IDUtils;
import net.qdjinxin.tinyurl.utils.SyncUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 金鑫
 * @title: TinyURLController
 * @projectName tiny-url
 * @description: 短网址管理服务
 * @date 2021/6/11 22:00
 */
@RestController
@RequestMapping("/api/tinyurl")
public class TinyURLController {
    /**
     * 根据短网址id获取网址url
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(String id) {
        return "";
    }

    /**
     * 根据id删除url
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(String id) {
        /**
         * 创建任务,待job执行时将索引删除
         */
        return "";
    }

    /**
     * 根据id创建url
     *
     * @param url
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public TinyURLDTO create(String url) {
        /**
         * 1. 首先创建对应的本地文件.
         * 2. 通过异步将对应的本地文件通知到相关服务器.
         * 3. 全部通知到位后,完成创建.
         */
        return SyncUtils.sync(url, IDUtils.createId());
    }
}
