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
    private boolean tracked;
    private Date created;

    public Torrent() {
    }

    public Torrent(String id, String hash, String name, String url, String magnet, boolean tracked, Date created) {
        this.id = id;
        this.hash = hash;
        this.name = name;
        this.url = url;
        this.magnet = magnet;
        this.tracked = tracked;
        this.created = created;
    }
}
