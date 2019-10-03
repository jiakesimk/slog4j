package com.sophon.component.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <h3>slog4j</h3>
 * <p>魔幻线程池</p>
 *
 * @author : https://github.com/AdlerED
 * @date : 2019-09-30 22:17
 **/
public class Pool {
    public static final ExecutorService logCache = Executors.newSingleThreadExecutor();
}