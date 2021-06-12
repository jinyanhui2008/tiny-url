package net.qdjinxin.tinyurl.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 金鑫
 * @title: SyncJob
 * @projectName tiny-url
 * @description: TODO
 * @date 2021/6/12 09:43
 */
@Component
@EnableScheduling
public class SyncJob {
    /**
     * 定时自动同步未共享的id到未同步服务
     */
    @Scheduled(cron = "0 * * * * ?")
    public void syncData(){

    }
}
