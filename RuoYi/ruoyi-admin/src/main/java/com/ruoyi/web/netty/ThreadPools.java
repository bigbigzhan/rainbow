package com.ruoyi.web.netty;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池
 * @author xlizy
 * @date 2018/5/11
 */
public class ThreadPools {

    public static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("publicUsePool-%d").build();

    public static ExecutorService publicUsePool = new ThreadPoolExecutor(8, 16,
                                   1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), threadFactory);


}
