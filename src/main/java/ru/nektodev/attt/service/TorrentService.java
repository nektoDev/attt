package ru.nektodev.attt.service;

import org.fluttercode.datafactory.impl.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nektodev.attt.api.dto.MediaKind;
import ru.nektodev.attt.model.Torrent;
import ru.nektodev.attt.model.TransmissionException;
import ru.nektodev.attt.parser.TrackerParser;
import ru.nektodev.attt.repository.TorrentRepository;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TorrentService {
    private static Logger LOG = LoggerFactory.getLogger(TorrentService.class);

    private final TorrentRepository torrentRepository;
    private final TransmissionService transmissionService;

    @Value("${default.download.directory}")
    private String defaultDownloadDirectory;

    @Autowired
    public TorrentService(TorrentRepository torrentRepository, TransmissionService transmissionService) {
        this.torrentRepository = torrentRepository;
        this.transmissionService = transmissionService;
    }

    @NotNull
    public List<Torrent> list() {
        return torrentRepository.findAll();
    }

    @Null
    public Torrent addByURL(@Nonnull String url, @Nonnull MediaKind kind) throws IOException, TransmissionException {
        TrackerParser parser = new TrackerParser();
        String magnet = parser.getMagnetFromUrl(url);

        //TODO define download dir
        String downloadPath = defaultDownloadDirectory + "/" + kind.getDefaultPath();
        String hash = transmissionService.addToTransmission(downloadPath, magnet);
        if (hash == null || hash.isEmpty()) {
            throw new TransmissionException(String.format("Cannot add magnet %s from URL %s to Transmission. The hash is null or empty", magnet, url));
        }

        Torrent result = Torrent.builder()
                .created(new Date())
                .url(url)
                .magnet(magnet)
                .hash(hash)
                .tracked(MediaKind.SERIES == kind)
                .build();

        return torrentRepository.save(result);
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
                    .tracked(false)
                    .url("https://rutracker.org/forum/viewtopic.php?t=" + df.getNumberText(7))
                    .build();
            generated.add(t);
        }
        return torrentRepository.save(generated);
    }
}
