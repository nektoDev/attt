package ru.nektodev.attt.api.dto;

import com.google.common.base.Objects;

public class TorrentAddRequest {
    private String url;
    private MediaKind kind;
    private String downloadDirectory;
    private String name;

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

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TorrentAddRequest{");
        sb.append("url='").append(url).append('\'');
        sb.append(", kind=").append(kind);
        sb.append(", downloadDirectory='").append(downloadDirectory).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TorrentAddRequest that = (TorrentAddRequest) o;
        return Objects.equal(url, that.url) &&
                kind == that.kind &&
                Objects.equal(downloadDirectory, that.downloadDirectory) &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, kind, downloadDirectory, name);
    }
}
