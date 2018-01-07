package ru.nektodev.attt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nektodev.attt.api.dto.TorrentGenerateRequest;
import ru.nektodev.attt.api.dto.TorrentTO;
import ru.nektodev.attt.model.Torrent;
import ru.nektodev.attt.service.TorrentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/torrent")
public class TorrentFacade {
    private final TorrentService torrentService;

    @Autowired
    public TorrentFacade(TorrentService torrentService) {
        this.torrentService = torrentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TorrentTO>> list() {
        List<Torrent> list = this.torrentService.list();
        List<TorrentTO> result = list.stream().map(TorrentTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TorrentTO> addByUrl(String url) {
        Torrent addedTorrent = this.torrentService.add(url);
        return ResponseEntity.ok(new TorrentTO(addedTorrent));
    }

    @RequestMapping(path = "/generate", method = RequestMethod.POST)
    public ResponseEntity<List<TorrentTO>> generate(@RequestBody TorrentGenerateRequest request) {
        List<Torrent> generated = this.torrentService.generate(request.getCount());
        return ResponseEntity.ok(generated.stream().map(TorrentTO::new).collect(Collectors.toList()));
    }

}
