package com.osaebros;

import com.osaebros.calculator.ConcurrentCalculator;
import com.osaebros.videoInformation.VideoPlayInfo;
import org.junit.jupiter.api.Test;
import java.time.Instant;


import static java.time.temporal.ChronoUnit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyTests {


    @Test
    public void testForShowsThatWerePlayingAtTheSameTimeAndEndedAtTheSameTime() {
        //given
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                new VideoPlayInfo(start, end),
                new VideoPlayInfo(start, end),
                new VideoPlayInfo(start, end),
        };
//        when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
//        assert
        assertEquals(3, actualCon);
    }

    @Test
    public void testForShowsThatStartedAtDifferentTimesButEndedAtTheSameTime() {
        //given
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                new VideoPlayInfo(start.plus(20, MINUTES), end),
                new VideoPlayInfo(start.plus(10, MINUTES), end),
                new VideoPlayInfo(start, end),
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
                new VideoPlayInfo(now, now.plus(1,HOURS)),
                new VideoPlayInfo(now.plus(30, MINUTES), now.plus(2,HOURS)),
                new VideoPlayInfo(now.plus(4,HOURS),now.plus(5,HOURS) ),
                new VideoPlayInfo(start, start.plus(1,HOURS)),
                new VideoPlayInfo(start, now),
                new VideoPlayInfo(now.minus(1,HOURS), now.plus(30,HOURS)),
                new VideoPlayInfo(now.plus(5,DAYS), now.plus(30,HOURS).plus(5, DAYS)),

        };
        //when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
        //assert
        assertEquals(6, actualCon);
    }



    @Test
    public void testForShowsThatHaveNoConcurrentRunningTimes(){
        //given
        ConcurrentCalculator concurrentCalculator = new ConcurrentCalculator();
        Instant start = Instant.now();
        Instant end = start.plus(1, HOURS);

        Instant startDiff = Instant.parse("2020-10-19T20:50:42.390530Z");
        Instant endDiff = startDiff.plus(30,MINUTES);


        VideoPlayInfo[] playinfo = new VideoPlayInfo[]{
                new VideoPlayInfo(start.plus(10, MINUTES), end),
                new VideoPlayInfo(startDiff, endDiff),

        };
//        when
        int actualCon = concurrentCalculator.getMaximumConCurrentPlays(playinfo);
//        assert
        assertEquals(0, actualCon);
    }

}
