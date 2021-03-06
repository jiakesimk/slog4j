package com.sophon.logger;

import com.sophon.component.io.SophonFile;
import com.sophon.config.Slog4jConfiguration;
import com.sophon.io.SophonWrite;
import com.sophon.io.SophonWriteBySize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统日志打印的实现类
 * @author tiansheng
 * @date 2019/9/13 3:43
 * @version 1.0
 * @since 1.8
 */
public class SystemLoggerImpl extends SophonLoggerImpl {

    private static final String formatString = "\\{\\}";

    // 日期格式化工具
    protected final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected SophonWrite write;

    public SystemLoggerImpl() {
        write = new SophonWriteBySize(2048,
                SophonFile.getFile(Slog4jConfiguration.getInstance().getLoggerSystemPrintPath()));
    }

    public SystemLoggerImpl(SophonFile file) {
        write = new SophonWriteBySize(2048, file);
    }

    /**
     * 异常信息输出
     * @param v
     */
    public void exception(String v){
        console(v,Level.ERROR);
    }

    @Override
    public String prefixGenerate(Level level, Thread t) {
        int trace = t.getStackTrace().length - 1;
        String className = t.getStackTrace()[trace].getClassName();
        String methodName = t.getStackTrace()[trace].getMethodName();
        String lineNumber = String.valueOf(t.getStackTrace()[trace].getLineNumber());
        String v = "${datetime} | ${class} ${method}:${line} | [FRAMEWORK][${level}] - ";
        return v.replaceAll("\\$\\{line\\}", lineNumber)
                .replaceAll("\\$\\{class\\}", className)
                .replaceAll("\\$\\{level\\}", String.valueOf(level))
                .replaceAll("\\$\\{method\\}", methodName)
                .replaceAll("\\$\\{datetime\\}", sdf.format(new Date()));
    }

    /**
     * 打印
     *
     * @param v
     */
    @Override
    protected synchronized void console(String v, Level level) {
        if (level == Level.ERROR || level == Level.WARN) {
            System.err.println(v);
        } else {
            System.out.println(v);
        }
        if(Slog4jConfiguration.getInstance().getLoggerPrintWrite()){
            write.write(v);
        }
    }

}
