package com.spauny.joy.roboscript.controller;

import com.spauny.joy.roboscript.services.LocalScriptHandlerService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author iulian.dafinoiu
 */
@Controller
public class MainController {
    
    @Autowired
    private LocalScriptHandlerService localScriptHandlerService;
    
    @RequestMapping("/startLocalScript")
    @ResponseBody
    public String startLocalScript(@RequestParam(value = "fullPath", required = true) String scriptFullPath) {
        return this.localScriptHandlerService.startLocalScript(scriptFullPath);
    }
    
    @RequestMapping("/stopLocalScript")
    @ResponseBody
    public String stopLocalScript(@RequestParam(value = "uniqueId", required = true) String scriptUniqueIdentifier) {
        return this.localScriptHandlerService.stopLocalScript(scriptUniqueIdentifier);
    }
    
    @RequestMapping("/showProcessLog")
    @ResponseBody
    public String showProcessLog(@RequestParam(value = "uniqueId", required = true) String scriptUniqueIdentifier) {
        return this.localScriptHandlerService.showProcessLog(scriptUniqueIdentifier);
    }
    
    @RequestMapping("/isProcessAlive")
    @ResponseBody
    public String isProcessAlive(@RequestParam(value = "uniqueId", required = true) String scriptUniqueIdentifier) {
        return this.localScriptHandlerService.isProcessAlive(scriptUniqueIdentifier);
    }
    
    @RequestMapping("/stopAllProcesses")
    @ResponseBody
    public String stopAllProcesses() {
        return this.localScriptHandlerService.stopAllProcesses();
    }
    
    @RequestMapping("/grantExecutePermissions")
    @ResponseBody
    public String grantExecutePermissions(@RequestParam(value = "fullPath", required = true) String scriptFullPath) {
        this.localScriptHandlerService.grantExecutePermissions(scriptFullPath);
        return "Execute permission applied for script: " + scriptFullPath;
    }
    
    // implement help endpoint here
    
    @RequestMapping("/help")
    @ResponseBody
    public String help(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Supported endpoints: ");
        sb.append("<ul>");
        sb.append("<li>");
        sb.append(request.getContextPath()).append("/startLocalScript?fullPath=<insert here the script's full path - this endpoint will return a unique id for your process>");
        sb.append("</li>");
        sb.append("<li>");
        sb.append(request.getContextPath()).append("/stopLocalScript?uniqueId=<insert here the unique id returnedby the start endpoint>");
        sb.append("</li>");
         sb.append("<li>");
        sb.append(request.getContextPath()).append("/showProcessLog?uniqueId=<insert here the unique id returnedby the start endpoint>");
        sb.append("</li>");
         sb.append("<li>");
        sb.append(request.getContextPath()).append("/isProcessAlive?uniqueId=<insert here the unique id returnedby the start endpoint>");
        sb.append("</li>");
        sb.append("<li>");
        sb.append(request.getContextPath()).append("/stopAllProcesses");
        sb.append("</li>");
        sb.append("<li>");
        sb.append(request.getContextPath()).append("/grantExecutePermissions?fullPath=");
        sb.append("</li>");
        sb.append("<li>");
        sb.append(request.getContextPath()).append("/help");
        sb.append("</li>");
        sb.append("</ul>");
        return sb.toString();
    }

}
