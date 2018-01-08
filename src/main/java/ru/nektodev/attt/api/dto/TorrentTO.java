package ru.nektodev.attt.api.dto;

import ru.nektodev.attt.model.Torrent;

import java.beans.Transient;
import java.util.Date;

public class TorrentTO {

    private String id;
    private String hash;
    private String name;
    private String url;
    private String magnet;
    private String downloadDirectory;
    private boolean tracked;
    private Date created;
    private Date lastChecked;
    private Date lastUpdated;

    public TorrentTO() {
    }

    public TorrentTO(Torrent t) {
        this.id = t.getId();
        this.hash = t.getHash();
        this.name = t.getName();
        this.url = t.getUrl();
        this.magnet = t.getMagnet();
        this.created = t.getCreated();
        this.tracked = t.isTracked();
        this.downloadDirectory = t.getDownloadDirectory();
        this.lastChecked = t.getLastChecked();
        this.lastUpdated = t.getLastUpdated();
    }

    @Transient
    public Torrent getTorrent() {
        return Torrent.builder()
                .created(this.created)
                .hash(this.hash)
                .id(this.id)
                .magnet(this.magnet)
                .name(this.name)
                .tracked(this.tracked)
                .url(this.url)
                .downloadDirectory(this.downloadDirectory)
                .lastChecked(this.lastChecked)
                .lastUpdated(this.lastUpdated)
                .build();
    }

    public String getId() {
        return this.id;
    }

    public String getHash() {
        return this.hash;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getMagnet() {
        return this.magnet;
    }

    public String getDownloadDirectory() {
        return this.downloadDirectory;
    }

    public boolean isTracked() {
        return this.tracked;
    }

    public Date getCreated() {
        return this.created;
    }

    public Date getLastChecked() {
        return this.lastChecked;
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }

    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    public void setTracked(boolean tracked) {
        this.tracked = tracked;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setLastChecked(Date lastChecked) {
        this.lastChecked = lastChecked;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TorrentTO)) return false;
        final TorrentTO other = (TorrentTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        if (this$hash == null ? other$hash != null : !this$hash.equals(other$hash)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$magnet = this.getMagnet();
        final Object other$magnet = other.getMagnet();
        if (this$magnet == null ? other$magnet != null : !this$magnet.equals(other$magnet)) return false;
        final Object this$downloadDirectory = this.getDownloadDirectory();
        final Object other$downloadDirectory = other.getDownloadDirectory();
        if (this$downloadDirectory == null ? other$downloadDirectory != null : !this$downloadDirectory.equals(other$downloadDirectory))
            return false;
        if (this.isTracked() != other.isTracked()) return false;
        final Object this$created = this.getCreated();
        final Object other$created = other.getCreated();
        if (this$created == null ? other$created != null : !this$created.equals(other$created)) return false;
        final Object this$lastChecked = this.getLastChecked();
        final Object other$lastChecked = other.getLastChecked();
        if (this$lastChecked == null ? other$lastChecked != null : !this$lastChecked.equals(other$lastChecked))
            return false;
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !this$lastUpdated.equals(other$lastUpdated))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $hash = this.getHash();
        result = result * PRIME + ($hash == null ? 43 : $hash.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $magnet = this.getMagnet();
        result = result * PRIME + ($magnet == null ? 43 : $magnet.hashCode());
        final Object $downloadDirectory = this.getDownloadDirectory();
        result = result * PRIME + ($downloadDirectory == null ? 43 : $downloadDirectory.hashCode());
        result = result * PRIME + (this.isTracked() ? 79 : 97);
        final Object $created = this.getCreated();
        result = result * PRIME + ($created == null ? 43 : $created.hashCode());
        final Object $lastChecked = this.getLastChecked();
        result = result * PRIME + ($lastChecked == null ? 43 : $lastChecked.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * PRIME + ($lastUpdated == null ? 43 : $lastUpdated.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof TorrentTO;
    }

    public String toString() {
        return "TorrentTO(id=" + this.getId() + ", hash=" + this.getHash() + ", name=" + this.getName() + ", url=" + this.getUrl() + ", magnet=" + this.getMagnet() + ", downloadDirectory=" + this.getDownloadDirectory() + ", tracked=" + this.isTracked() + ", created=" + this.getCreated() + ", lastChecked=" + this.getLastChecked() + ", lastUpdated=" + this.getLastUpdated() + ")";
    }
}
