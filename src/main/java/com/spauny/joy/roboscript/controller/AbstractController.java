package com.spauny.joy.roboscript.controller;

import com.spauny.joy.roboscript.app.GlobalManager;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractController implements Serializable {
    private static final long serialVersionUID = 19784657845612384L;
    
    @Autowired
    protected GlobalManager globalManager;
    
}