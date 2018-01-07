package ru.nektodev.attt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nektodev.attt.model.Torrent;

public interface TorrentRepository extends MongoRepository<Torrent, String> {
}
