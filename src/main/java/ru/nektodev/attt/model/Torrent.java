package ru.nektodev.attt.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Torrent {
    @Id
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

    public Torrent() {
    }

    public Torrent(String id, String hash, String name, String url, String magnet, String downloadDirectory, boolean tracked, Date created, Date lastChecked, Date lastUpdated) {
        this.id = id;
        this.hash = hash;
        this.name = name;
        this.url = url;
        this.magnet = magnet;
        this.downloadDirectory = downloadDirectory;
        this.tracked = tracked;
        this.created = created;
        this.lastChecked = lastChecked;
        this.lastUpdated = lastUpdated;
    }
}
