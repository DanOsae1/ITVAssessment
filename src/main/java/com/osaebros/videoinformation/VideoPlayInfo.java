package com.osaebros.videoinformation;

import java.time.Instant;
import java.util.Objects;

public class VideoPlayInfo {


    private Instant startTime;
    private Instant endTime;

    public VideoPlayInfo(Builder builder) {
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
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

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder {

        private Instant startTime;
        private Instant endTime;

        public Builder() {
        }

        public Builder setStartTime(Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(Instant endTime) {
            this.endTime = endTime;
            return this;
        }

        public VideoPlayInfo build() {
            return new VideoPlayInfo(this);

        }

    }
}
