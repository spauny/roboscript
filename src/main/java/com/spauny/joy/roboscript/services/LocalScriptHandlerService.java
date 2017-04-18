package com.spauny.joy.roboscript.services;

/**
 *
 * @author Iulian
 */
public interface LocalScriptHandlerService {

    /**
     *
     * @param scriptFullPath
     */
    void grantExecutePermissions(String scriptFullPath);

    /**
     *
     * @param scriptFullPath
     * @return
     */
    String startLocalScript(String scriptFullPath);

    /**
     *
     * @param scriptUniqueIdentifier
     * @return
     */
    String stopLocalScript(String scriptUniqueIdentifier);

    /**
     *
     * @return
     */
    String stopAllProcesses();

    String showProcessLog(String scriptUniqueIdentifier);

    String isProcessAlive(String scriptUniqueIdentifier);
    
}
