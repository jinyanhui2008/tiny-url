package net.qdjinxin.tinyurl.controller;

import net.qdjinxin.tinyurl.dto.TinyURLDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 金鑫
 * @title: TinyURLController
 * @projectName tiny-url
 * @description: TODO
 * @date 2021/6/11 22:00
 */
@RestController
@RequestMapping("/api/tinyurl")
public class TinyURLController {
    /**
     * 根据短网址id获取网址url
     * @param id
     * @return
     */
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(String id) {
        return "";
    }

    /**
     * 根据id删除url
     * @param id
     * @return
     */
    @GetMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(String id) {
        return "";
    }

    /**
     * 根据id创建url
     * @param path
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public TinyURLDTO create(String path) {
        return new TinyURLDTO();
    }
}
