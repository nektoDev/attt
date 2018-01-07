package ru.nektodev.attt.api.dto;

import java.util.Objects;

public class TorrentGenerateRequest {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorrentGenerateRequest that = (TorrentGenerateRequest) o;
        return Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "TorrentGenerateRequest{" + "count=" + count + '}';
    }
}
