package com.example;

import java.time.LocalDateTime;

public class ShowTimeJob implements Job {
    private LocalDateTime jobTime;

    public ShowTimeJob(LocalDateTime jobTime) {
        this.jobTime = jobTime;
    }

    @Override
    public void run() {
        System.out.println("ShowTimeJob: " + jobTime);
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