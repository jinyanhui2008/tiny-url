package net.qdjinxin.tinyurl.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author 金鑫
 * @title: TinyURLDTO
 * @projectName tiny-url
 * @description: 短网址对象
 * @date 2021/6/11 22:05
 */
@Data
@Builder
public class TinyURLDTO {
    /**
     * 短网址id
     */
    String id;
    /**
     * 短网址连接
     */
    String tiny_url;
    /**
     * 集群分发成功
     */
    Boolean sync;
}
