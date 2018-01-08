package ru.nektodev.attt.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nektodev.attt.api.dto.TorrentAddRequest;
import ru.nektodev.attt.api.dto.TorrentGenerateRequest;
import ru.nektodev.attt.api.dto.TorrentTO;
import ru.nektodev.attt.model.Torrent;
import ru.nektodev.attt.model.TrackerParserException;
import ru.nektodev.attt.model.TransmissionException;
import ru.nektodev.attt.service.TorrentService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/torrent")
public class TorrentFacade {
    private static Logger LOG = LoggerFactory.getLogger(TorrentFacade.class);

    private final TorrentService torrentService;

    @Autowired
    public TorrentFacade(TorrentService torrentService) {
        this.torrentService = torrentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<TorrentTO>> list() {
        List<Torrent> list = this.torrentService.list();
        List<TorrentTO> result = list.stream().map(TorrentTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<TorrentTO> addByUrl(@RequestBody TorrentAddRequest request) {
        validateAddTorrentRequest(request);

        try {
            Torrent addedTorrent = this.torrentService.addByURL(
                    request.getUrl(),
                    request.getKind(),
                    request.getDownloadDirectory(),
                    request.getName()
            );
            return ResponseEntity.ok(new TorrentTO(addedTorrent));
        } catch (IOException | TransmissionException | TrackerParserException e) {
            LOG.error("Cannot add torrent. ", e);
            throw new RuntimeException(e);
        }
    }

    private void validateAddTorrentRequest(TorrentAddRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if (request.getKind() == null) {
            throw new IllegalArgumentException("Media Kind cannot be null");
        }
    }


    @RequestMapping(path = "/generate", method = RequestMethod.POST)
    public ResponseEntity<List<TorrentTO>> generate(@RequestBody TorrentGenerateRequest request) {
        List<Torrent> generated = this.torrentService.generate(request.getCount());
        return ResponseEntity.ok(generated.stream().map(TorrentTO::new).collect(Collectors.toList()));
    }


}
