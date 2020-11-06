package com.osaebros;

import com.osaebros.calculator.ConcurrentCalculator;
import com.osaebros.videoinformation.VideoPlayInfo;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Application {

    public static void main(String[] args) {
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        VideoPlayInfo show1 = VideoPlayInfo.builder()
                .setEndTime(end)
                .setStartTime(start)
                .build();

        VideoPlayInfo show2 = VideoPlayInfo.builder()
                .setEndTime(end)
                .setStartTime(start.plus(10, MINUTES))
                .build();

        VideoPlayInfo show3 = VideoPlayInfo.builder()
                .setEndTime(end)
                .setStartTime(start.plus(20, MINUTES))
                .build();

        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                show1, show2, show3
        };

        Integer maximumConcurrency = concurrentCalculator.getMaximumConCurrentPlays(playinfo);

        System.out.println("Number of concurrent videos " + maximumConcurrency);

    }
}
