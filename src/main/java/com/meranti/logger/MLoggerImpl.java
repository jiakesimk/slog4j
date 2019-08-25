package com.meranti.logger;

import com.meranti.config.ConfigVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Mr.luo
 * @Date 2019/8/24 23:56
 */
public final class MLoggerImpl implements MerantiLogger {

    /**
     * 这是线程追踪数组的值
     * 为了方便理解,举个栗子:
     * 当前有 A,B 两个类
     * <p>
     * A 如果直接调用 mlog.info("xxx"),那么上级线程就应该是 4!
     * <p>
     * 它们的调用关系是这样的: A ---> mlog ---> MLoggerImpl
     * <p>
     * 所以我们 info 方法调用的线程就是 A, 所以 A 的线程值也就是4!
     * <p>
     * -----------------------------------------------------------------------------
     * <p>
     * 再假设 A 封装了 mlog 类,B 通过 A.info("xxx") 来进行日志打印
     * <p>
     * 现在关系图就是这样了: B ---> A ---> mlog ---> MLoggerImpl
     * <p>
     * 那么 trace 值就应该为 5 了,因为经过了 B、A、mlog、MloggerImpl 4 个类,然后以此类推
     * <p>
     * 简单的说就是经过了一个类,追踪值为 2,经过两个 追踪值为 3,经过了三个追踪值为 4
     */
    private int trace = 2;

    // 日期格式化工具
    private static final DateFormat sdf = ConfigVo.getSimpleDateFormat();

    // 日期打印模板
    private static final String printTemplate = ConfigVo.getLoggerPrintTemplate();

    public MLoggerImpl() {
    }

    /**
     * 设置追踪哪个线程
     *
     * @param trace
     */
    public MLoggerImpl(int trace) {
        this.trace = trace;
    }

    @Override
    public void info(String v) {
        v = prefixGenerate("INFO").concat(v);
        System.out.println(v);
    }

    @Override
    public void debug(String v) {
        v = prefixGenerate("DEBUG").concat(v);
        System.out.println(v);
    }

    @Override
    public void error(String v) {
        v = prefixGenerate("ERROR").concat(v);
        System.out.println(v);
    }

    @Override
    public void warn(String v) {
        v = prefixGenerate("WARN").concat(v);
        System.out.println(v);
    }

    @Override
    public void exception(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String exceptionInfo = stringWriter.toString();
        System.out.println(exceptionInfo);
        // 关闭流
        try {
            printWriter.close();
            stringWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 前缀生成
     * @param level 级别名称,例如:INFO DEBUG ERROR
     * @return
     */
    public String prefixGenerate(String level) {
        String className = Thread.currentThread().getStackTrace()[trace].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(Thread.currentThread().getStackTrace()[trace].getLineNumber());
        String v = printTemplate;
        return v.replaceAll("\\$\\{line\\}", lineNumber)
                .replaceAll("\\$\\{class\\}", className)
                .replaceAll("\\$\\{level\\}", level)
                .replaceAll("\\$\\{method\\}", methodName)
                .replaceAll("\\$\\{datetime\\}", sdf.format(new Date()));
    }

}