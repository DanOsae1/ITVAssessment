package com.osaebros.videoInformation;

import java.time.Instant;
import java.util.Objects;

public class VideoPlayInfo {

    Instant startTime;
    Instant endTime;

    public VideoPlayInfo(Instant startTime, Instant endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoPlayInfo)) return false;
        VideoPlayInfo that = (VideoPlayInfo) o;
        return Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
