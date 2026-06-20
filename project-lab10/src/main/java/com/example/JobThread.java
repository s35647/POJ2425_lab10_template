package com.example;

public class JobThread extends Thread {
    private final Job job;

    public JobThread(Job job) {
        this.job = job;
    }

    @Override
    public void run() {
        if (job != null) {
            job.run();
        }
    }
}