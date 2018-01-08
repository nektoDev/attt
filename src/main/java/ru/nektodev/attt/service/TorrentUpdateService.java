package ru.nektodev.attt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nektodev.attt.model.Torrent;
import ru.nektodev.attt.model.TrackerParserException;
import ru.nektodev.attt.model.TransmissionException;
import ru.nektodev.attt.parser.TrackerParser;
import ru.nektodev.attt.repository.TorrentRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class TorrentUpdateService {
    public static final Logger LOG = LoggerFactory.getLogger(TorrentUpdateService.class);

    private final TorrentRepository torrentRepository;
    private final TransmissionService transmissionService;

    private final TrackerParser parser = new TrackerParser();

    @Autowired
    public TorrentUpdateService(TorrentRepository torrentRepository, TransmissionService transmissionService) {
        this.torrentRepository = torrentRepository;
        this.transmissionService = transmissionService;
    }

    public void checkAllForUpdates() {
        List<Torrent> tracked = torrentRepository.findAllByTracked(true);
        for (Torrent torrent : tracked) {
            if (torrent.getUrl() == null || torrent.getUrl().isEmpty()) {
                LOG.warn(String.format("Torrent id=%s, name=%s does not have url", torrent.getId(), torrent.getName()));
                continue;
            }

            try {
                String magnet = parser.getMagnetFromUrl(torrent.getUrl());
                torrent.setLastChecked(new Date());

                if (!magnet.equalsIgnoreCase(torrent.getMagnet())) {
                    LOG.info(String.format("Torrent id=%s, name=%s has been updated!", torrent.getId(), torrent.getName()));
                    String hash = transmissionService.addToTransmission(torrent.getDownloadDirectory(), magnet);
                    torrent.setHash(hash);
                    torrent.setLastUpdated(new Date());
                    torrent.setMagnet(magnet);
                }

                torrentRepository.save(torrent);

            } catch (TrackerParserException | IOException | TransmissionException e) {
                LOG.warn(String.format("Cannot get magnet from URL %s for torrent id=%s, name=%s.", torrent.getUrl(), torrent.getId(), torrent.getName()), e.getMessage());
            }
        }
    }
}
