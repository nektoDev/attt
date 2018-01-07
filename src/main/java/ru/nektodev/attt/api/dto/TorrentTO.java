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
    private Date created;
    private boolean tracked;

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
                .build();
    }
}
