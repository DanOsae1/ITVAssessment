package com.osaebros.calculator;

import com.osaebros.videoinformation.VideoPlayInfo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcurrentCalculator {


    public int getMaximumConCurrentPlays(VideoPlayInfo[] playinfo) {
        List<VideoPlayInfo> vinfo = Stream.of(playinfo)
                .sorted(Comparator.comparing(VideoPlayInfo::getStartTime))
                .collect(Collectors.toList());

        List<VideoPlayInfo> concurrencyList = new ArrayList<>();
        for (int i = 0; i < vinfo.size() - 1; i++) {

            for (int j = i + 1; j < vinfo.size(); j++) {
                VideoPlayInfo vi = vinfo.get(i);
                VideoPlayInfo vj = vinfo.get(j);

                if (i != j && vi.getStartTime().equals(vj.getStartTime()) || (vj.getStartTime().isAfter(vi.getStartTime()) && vj.getStartTime().isBefore(vi.getEndTime()))) {
                    concurrencyList.add(vi);
                }
            }
        }

        return concurrencyList.size();
    }
}


