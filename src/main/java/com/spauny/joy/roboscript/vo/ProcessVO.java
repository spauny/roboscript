package com.spauny.joy.roboscript.vo;

import java.io.OutputStream;
import lombok.Data;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 *
 * @author iulian
 */
@Data
public class ProcessVO {
    private ExecuteWatchdog watchdog;
    private PumpStreamHandler streamHandler;
    private OutputStream outputStream;
}
