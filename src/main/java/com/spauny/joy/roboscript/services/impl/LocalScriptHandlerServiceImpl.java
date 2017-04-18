package com.spauny.joy.roboscript.services.impl;

import com.spauny.joy.roboscript.services.LocalScriptHandlerService;
import com.spauny.joy.roboscript.vo.ProcessVO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

/**
 *
 * @author Iulian
 */
@Slf4j
@Service
public class LocalScriptHandlerServiceImpl implements LocalScriptHandlerService {

    private final Map<String, ProcessVO> currentProcesses = new HashMap<>();

    /**
     *
     * @param scriptFullPath
     * @return
     */
    @Override
    public String startLocalScript(String scriptFullPath) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CommandLine cmdLine = CommandLine.parse(scriptFullPath);
            DefaultExecutor executor = new DefaultExecutor();
            ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            executor.setWatchdog(watchdog);
            executor.setStreamHandler(streamHandler);
            executor.setExitValue(0);
            
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            executor.execute(cmdLine, resultHandler);

            ProcessVO processVO = new ProcessVO();
            processVO.setStreamHandler(streamHandler);
            processVO.setWatchdog(watchdog);
            processVO.setOutputStream(outputStream);

            String scriptUniqueIdentifier = UUID.randomUUID().toString();
            while (currentProcesses.containsKey(scriptUniqueIdentifier)) {
                scriptUniqueIdentifier = UUID.randomUUID().toString();
            }
            currentProcesses.put(scriptUniqueIdentifier, processVO);
            return scriptUniqueIdentifier;
        } catch (IOException ex) {
            log.error("startLocalScript: ", ex);
            return "Error occured while executing script. Double check the script's full path and make sure it's executable.";
        }
    }

    /**
     *
     * @param scriptUniqueIdentifier
     * @return
     */
    @Override
    public String stopLocalScript(String scriptUniqueIdentifier) {
        if (currentProcesses.containsKey(scriptUniqueIdentifier)) {
            ProcessVO currentProcess = currentProcesses.get(scriptUniqueIdentifier);
            if (currentProcess != null) {
                currentProcess.getWatchdog().destroyProcess();
                currentProcesses.remove(scriptUniqueIdentifier);
                return "Process with ID: " + scriptUniqueIdentifier + " has been successfully stopped.";
            } else {
                return "Process with ID: " + scriptUniqueIdentifier + " was not found. The process might have been terminated already.";
            }
        } else {
            return "Process with ID: " + scriptUniqueIdentifier + " was not found. Make sure you're supplying the correct unique identifier.";
        }
    }

    /**
     *
     * @param scriptUniqueIdentifier
     * @return
     */
    @Override
    public String showProcessLog(String scriptUniqueIdentifier) {
        if (currentProcesses.containsKey(scriptUniqueIdentifier)) {
            ProcessVO currentProcess = currentProcesses.get(scriptUniqueIdentifier);
            if (currentProcess != null) {
                return currentProcess.getOutputStream().toString();
            } else {
                return "Process with ID: " + scriptUniqueIdentifier + " was not found. Something is wrong!";
            }
        } else {
            return "Process with ID: " + scriptUniqueIdentifier + " was not found. Make sure you're supplying the correct unique identifier.";
        }
    }

    /**
     *
     * @param scriptUniqueIdentifier
     * @return
     */
    @Override
    public String isProcessAlive(String scriptUniqueIdentifier) {
        if (currentProcesses.containsKey(scriptUniqueIdentifier)) {
            ProcessVO currentProcess = currentProcesses.get(scriptUniqueIdentifier);
            if (currentProcess != null) {
                return Boolean.toString(currentProcess.getWatchdog().isWatching());
            } else {
                return "Process with ID: " + scriptUniqueIdentifier + " was not found. Something is wrong!";
            }
        } else {
            return "Process with ID: " + scriptUniqueIdentifier + " was not found. Make sure you're supplying the correct unique identifier.";
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String stopAllProcesses() {
        for (String scriptUniqueIdentifier : currentProcesses.keySet()) {
            ProcessVO currentProcess = currentProcesses.get(scriptUniqueIdentifier);
            if (currentProcess != null) {
                currentProcess.getWatchdog().destroyProcess();
            }
        }
        currentProcesses.clear();
        return "All processes have been stopped.";
    }

    /**
     *
     * @param scriptFullPath
     */
    @Override
    public void grantExecutePermissions(String scriptFullPath) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("chmod -R u+x " + scriptFullPath);
        } catch (IOException ex) {
            log.error("grantExecutePermissions: ", ex);
        }
    }

}
