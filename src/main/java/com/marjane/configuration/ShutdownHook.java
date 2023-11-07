package com.marjane.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownHook extends Thread {
    @Override
    public void run() {
        AppInit.stop();
    }
}