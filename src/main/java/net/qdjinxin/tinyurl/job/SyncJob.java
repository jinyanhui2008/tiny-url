package net.qdjinxin.tinyurl.job;

import net.qdjinxin.tinyurl.dto.TinyURLDTO;
import net.qdjinxin.tinyurl.utils.FileUtils;
import net.qdjinxin.tinyurl.utils.SyncUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

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
     * 每隔5秒运行一次同步任务
     * 定时自动同步未共享的id到未同步服务
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void syncData() {
        File dir = new File("./sync");
        final File[] files = dir.listFiles();
        for (File file : files) {
            String url = FileUtils.readFile(file);
            String id = file.getName().replace(".sync", "");
            final TinyURLDTO sync = SyncUtils.sync(url, id);
            if (sync.getSync()) {
                file.delete();
            }
        }
    }
}
