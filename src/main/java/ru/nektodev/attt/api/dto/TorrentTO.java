package ru.nektodev.attt.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.nektodev.attt.model.Torrent;

import java.beans.Transient;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
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
}
