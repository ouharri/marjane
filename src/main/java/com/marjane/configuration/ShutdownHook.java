package com.marjane.configuration;

import org.springframework.stereotype.Component;

@Component
public class ShutdownHook extends Thread {
    @Override
    public void run() {
        AppInit.stop();
    }
}