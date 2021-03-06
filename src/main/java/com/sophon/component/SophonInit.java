package com.sophon.component;

/**
 * 类的初始化接口
 * @author tiansheng
 * @date 2019/9/14 4:12
 * @version 1.0
 * @since 1.8
 */
public interface SophonInit {

    /**
     * 接口初始化方法,如果类有static静态块需要被初始化
     * 又不用做其他操作的时候,此方法可以为空方法
     */
    void init();

}
