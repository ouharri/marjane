package com.marjane.core;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownHook extends Thread {
    @Override
    public void run() {
        Application.stop();
    }
}