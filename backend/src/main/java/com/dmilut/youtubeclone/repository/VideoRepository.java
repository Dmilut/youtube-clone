package com.dmilut.youtubeclone.repository;

import com.dmilut.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
