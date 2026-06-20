package com.example;

import java.time.LocalDateTime;

public class SimpleJobScheduler implements JobScheduler {
    private Job job;
    private LocalDateTime nextExecutionTime;
    private int intervalSeconds = 0;
    private int remainingRepeats = -1;
    private boolean isFinished = false;

    @Override
    public JobScheduler forJob(Job job) {
        this.job = job;
        if (this.nextExecutionTime == null) {
            this.nextExecutionTime = LocalDateTime.now();
        }
        return this;
    }

    @Override
    public JobScheduler startsAt(LocalDateTime time) {
        this.nextExecutionTime = time;
        return this;
    }

    @Override
    public JobScheduler everySeconds(int seconds) {
        this.intervalSeconds = seconds;
        return this;
    }

    @Override
    public JobScheduler repeatTimes(int times) {
        this.remainingRepeats = times == 0 ? -1 : times;
        return this;
    }

    @Override
    public void listenTo(TimeEvent event) {
        if (isFinished || job == null || nextExecutionTime == null) {
            return;
        }

        LocalDateTime currentTime = event.getTime();

        if (!currentTime.isBefore(nextExecutionTime)) {

            job.setJobTime(currentTime);

            new JobThread(job).start();

            if (remainingRepeats > 0) {
                remainingRepeats--;
                if (remainingRepeats == 0) {
                    isFinished = true;
                    return;
                }
            }

            if (intervalSeconds > 0) {
                nextExecutionTime = currentTime.plusSeconds(intervalSeconds);
            } else {
                isFinished = true;
            }
        }
    }
}