package ru.nektodev.attt.api.dto;

import com.google.common.base.Objects;

public class TorrentAddRequest {
    private String url;
    private MediaKind kind;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MediaKind getKind() {
        return kind;
    }

    public void setKind(MediaKind kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TorrentAddRequest{");
        sb.append("url='").append(url).append('\'');
        sb.append(", kind=").append(kind);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorrentAddRequest that = (TorrentAddRequest) o;
        return Objects.equal(url, that.url) &&
                Objects.equal(kind, that.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, kind);
    }
}
