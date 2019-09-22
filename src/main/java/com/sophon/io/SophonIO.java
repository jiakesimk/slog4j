package com.sophon.io;

import com.sophon.component.io.SophonFile;
import com.sophon.config.Slog4jConfiguration;

/**
 * @Author tiansheng
 * @Date 2019/9/11 2:13
 * @Description TODO
 */
public class SophonIO {

    public static SophonWrite getWrite(){
        // 数据写出接口
        SophonWrite write;
        // 获取要操作的文件
        SophonFile file = SophonFile.getFile(Slog4jConfiguration.getInstance().getLoggerPrintPath());
        // 获取数据写出接口实例
        String[] rule = Slog4jConfiguration.getInstance().getLoggerGenerateRule();
        switch (rule[0]){
            case SophonWrite.BYSIZE:
                write = new SophonWriteBySize(Integer.parseInt(rule[1]),file);
                break;
            default:
                write = new SophonWriteBySize(1024,file);
                break;
        }
        return write;
    }

}
