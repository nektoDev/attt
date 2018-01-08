package ru.nektodev.attt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nektodev.attt.model.Torrent;

import java.util.List;

public interface TorrentRepository extends MongoRepository<Torrent, String> {
    List<Torrent> findAllByTracked(boolean tracked);
}
