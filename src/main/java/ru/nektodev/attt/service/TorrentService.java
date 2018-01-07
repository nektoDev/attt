package ru.nektodev.attt.service;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nektodev.attt.model.Torrent;
import ru.nektodev.attt.repository.TorrentRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TorrentService {
    private final TorrentRepository torrentRepository;

    @Autowired
    public TorrentService(TorrentRepository torrentRepository) {
        this.torrentRepository = torrentRepository;
    }

    @NotNull
    public List<Torrent> list() {
        return new ArrayList<>();
    }

    @Null
    public Torrent add(@Null String url) {
        return null;
    }

    @NotNull
    public List<Torrent> generate(@Null Integer count) {
        if (count == null || count < 1) {
            throw new IllegalArgumentException(String.format("Count cannot be less than 1: count=%d", count));
        }
        DataFactory df = new DataFactory();
        ArrayList<Torrent> generated = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Torrent t = Torrent.builder()
                    .created(new Date())
                    .hash(df.getRandomChars(32))
                    .magnet(df.getRandomChars(32))
                    .name(df.getName())
                    .tracked(df.chance(50))
                    .url("https://rutracker.org/forum/viewtopic.php?t=" + df.getNumberText(7))
                    .build();
            generated.add(t);
        }
        List<Torrent> saved = torrentRepository.save(generated);
        return saved;
    }
}
