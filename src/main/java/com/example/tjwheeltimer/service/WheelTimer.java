package com.example.tjwheeltimer.service;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WheelTimer {

    private HashedWheelTimer hashedWheelTimer;

    @PostConstruct
    public void init() {
        hashedWheelTimer = new HashedWheelTimer(
                Executors.defaultThreadFactory(),
                1000L,
                TimeUnit.MILLISECONDS,
                50,
                true,
                0
        );
        hashedWheelTimer.start();
        log.info("hashedWheelTimer start. : {}", hashedWheelTimer);
    }

    public void deliver() throws Exception {
        log.info("{}", "Timeouts : " + hashedWheelTimer.pendingTimeouts());
        SimpleTimerTask simpleTimerTask = new SimpleTimerTask();
        hashedWheelTimer.newTimeout(simpleTimerTask, hashedWheelTimer.pendingTimeouts() * 1000L, TimeUnit.MILLISECONDS);
    }

    class SimpleTimerTask implements TimerTask {
        public SimpleTimerTask(){
        }
        @Override
        public void run(Timeout timeout) throws Exception {
            try {
                Thread.sleep(1000L);
            } catch(Exception e) {
                log.info("Error on : {}" + e.getMessage());
            } finally {
                log.info("{}", "POST Timeouts: " + ((HashedWheelTimer)timeout.timer()).pendingTimeouts());
            }
        }
    }
}
