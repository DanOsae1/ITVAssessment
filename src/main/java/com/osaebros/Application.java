package com.osaebros;

import com.osaebros.calculator.ConcurrentCalculator;
import com.osaebros.videoInformation.VideoPlayInfo;
import java.time.Instant;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Application {

    public static void main(String[] args) {
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                new VideoPlayInfo(start.plus(10, MINUTES), end),
                new VideoPlayInfo(start.plus(20, MINUTES), end),
                new VideoPlayInfo(start, end),

        };

        Integer maximumConcurrency = concurrentCalculator.getMaximumConCurrentPlays(playinfo);

        System.out.println("Number of concurrent videos " + maximumConcurrency);

    }
}
