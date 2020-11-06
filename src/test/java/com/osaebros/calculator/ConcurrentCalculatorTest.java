package com.osaebros.calculator;

import com.osaebros.videoinformation.VideoPlayInfo;
import org.junit.jupiter.api.Test;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentCalculatorTest {

    @Test
    public void testForShowsThatWerePlayingAtTheSameTimeAndEndedAtTheSameTime() {
        //given
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        VideoPlayInfo show = VideoPlayInfo.builder()
                .setStartTime(start)
                .setEndTime(end)
                .build();

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                show, show, show
        };

        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();

//        when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
//        assert
        assertEquals(3, actualCon);
    }


    @Test
    public void testForShowsThatStartedAtDifferentTimesButEndedAtTheSameTime() {
        //given
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();


        VideoPlayInfo show = VideoPlayInfo.builder()
                .setStartTime(start.plus(20, MINUTES))
                .setEndTime(end)
                .build();
        VideoPlayInfo show1 = VideoPlayInfo.builder()
                .setStartTime(start)
                .setEndTime(end)
                .build();
        VideoPlayInfo show2 = VideoPlayInfo.builder()
                .setStartTime(start.plus(10, MINUTES))
                .setEndTime(end)
                .build();

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                show,
                show1,
                show2
        };
//        when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
//        assert
        assertEquals(3, actualCon);
    }


    @Test
    public void testForShowsThatWereStartedWhilstOneWasPlaying() {
        //given
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        Instant now = Instant.now();
        Instant start = Instant.parse("2020-10-19T20:50:42.390530Z");

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                VideoPlayInfo.builder().setStartTime(now).setEndTime(now.plus(1, HOURS)).build(),
                VideoPlayInfo.builder().setStartTime(now.plus(30, MINUTES)).setEndTime(now.plus(2, HOURS)).build(),
                VideoPlayInfo.builder().setStartTime(now.plus(4, HOURS)).setEndTime(now.plus(5, HOURS)).build(),
                VideoPlayInfo.builder().setStartTime(start).setEndTime(start.plus(1, HOURS)).build(),
                VideoPlayInfo.builder().setStartTime(start).setEndTime(now).build(),
                VideoPlayInfo.builder().setStartTime(now.minus(1, HOURS)).setEndTime(now.plus(30, HOURS)).build(),
                VideoPlayInfo.builder().setStartTime(now.plus(5, DAYS)).setEndTime(now.plus(30, HOURS).plus(5, DAYS)).build(),

        };
        //when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
        //assert
        assertEquals(6, actualCon);
    }

    @Test
    public void testForShowsThatHaveNoConcurrentRunningTimes() {
        //given
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        Instant startDiff = Instant.parse("2020-10-19T20:50:42.390530Z");
        Instant endDiff = startDiff.plus(30, MINUTES);

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                VideoPlayInfo.builder().setStartTime(start.plus(10, MINUTES)).setEndTime(end).build(),
                VideoPlayInfo.builder().setStartTime(startDiff).setEndTime(endDiff).build(),

        };
//        when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
//        assert
        assertEquals(0, actualCon);
    }

}