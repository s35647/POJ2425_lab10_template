package com.example;

import java.time.LocalDateTime;

public class DescribedJob implements Job {
    private final String description;
    private LocalDateTime jobTime;

    public DescribedJob(String description) {
        this.description = description;
        this.jobTime = LocalDateTime.now();
    }

    @Override
    public void run() {
        System.out.println("DescribedJob [" + description + "] uruchomiony o: " + LocalDateTime.now());
    }

    @Override
    public void setJobTime(LocalDateTime time) {
        this.jobTime = time;
    }

    @Override
    public LocalDateTime getJobTime() {
        return jobTime;
    }
}