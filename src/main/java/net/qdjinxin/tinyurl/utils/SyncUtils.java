package net.qdjinxin.tinyurl.utils;

import lombok.extern.log4j.Log4j2;
import net.qdjinxin.tinyurl.dto.TinyURLDTO;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 金鑫
 * @title: SyncUtils
 * @projectName tiny-url
 * @description: 同步服务
 * @date 2021/6/12 09:53
 */
@Component
@Log4j2
public class SyncUtils {
    protected static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS) //设置合理的超时
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).connectionPool(new ConnectionPool(5, 1, TimeUnit.MINUTES)).build();
    private static String[] serviceUrl;

    @Value("${tiny.server}")
    public void setServiceUrl(String[] serviceUrl) {
        SyncUtils.serviceUrl = serviceUrl;
    }

    /**
     * 将短网址同步分发到相应的服务服务器上
     *
     * @param url
     * @param id
     */
    public static TinyURLDTO sync(String url, String id) {
        AtomicReference<Boolean> ok = new AtomicReference<>(true);
        AtomicReference<String> tinyUrl = new AtomicReference<>("");
        Arrays.stream(serviceUrl).forEach(sericeUrl -> {
            Request.Builder builder = new Request.Builder();
            FormBody formBody = new FormBody.Builder().add("url", url).add("id", id).build();
            Request request = builder.url("http://" + sericeUrl + "/api/sync/sync").post(formBody).build();
            final Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                tinyUrl.set(response.body().string());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                //如果调用异常则将请求放入缓存,等待job继续同步
                File sync = new File("./sync/" + id + ".sync");
                FileUtils.writeFile(sync, url);
                ok.set(false);
            }
        });
        return TinyURLDTO.builder().id(id).tiny_url(tinyUrl.get()).sync(ok.get()).build();
    }
}
