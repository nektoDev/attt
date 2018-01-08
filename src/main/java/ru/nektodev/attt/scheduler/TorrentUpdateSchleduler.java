package ru.nektodev.attt.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nektodev.attt.service.TorrentUpdateService;

import java.io.IOException;

@Component
public class TorrentUpdateSchleduler {
    private static final Logger LOG = Logger.getLogger(TorrentUpdateSchleduler.class);

    private final TorrentUpdateService torrentUpdateService;

    @Autowired
    public TorrentUpdateSchleduler(TorrentUpdateService torrentUpdateService) {
        this.torrentUpdateService = torrentUpdateService;
    }

    @Scheduled(cron = "${scheduler.check.all.updates.cron}")
    private void scheduledCheckForUpdates() throws IOException {
        LOG.info("Start scheduled check.");
        torrentUpdateService.checkAllForUpdates();
        LOG.info("Scheduled check completed");
    }
}
