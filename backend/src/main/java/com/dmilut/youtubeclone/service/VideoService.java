package com.dmilut.youtubeclone.service;

import com.dmilut.youtubeclone.dto.VideoDTO;
import com.dmilut.youtubeclone.model.Video;
import com.dmilut.youtubeclone.model.VideoStatus;
import com.dmilut.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        Video video = new Video();
        video.setVideoUrl(videoUrl);

        videoRepository.save(video);
    }

    public VideoDTO editVideo(VideoDTO videoDTO) {
        Video savedVideo = videoRepository.findById(videoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id " + videoDTO.getId()));

        savedVideo.setTitle(videoDTO.getTitle());
        savedVideo.setDescription(videoDTO.getDescription());
        savedVideo.setTags(videoDTO.getTags());
        savedVideo.setVideoStatus(videoDTO.getVideoStatus());
        savedVideo.setThumbnailUrl(videoDTO.getThumbnailUrl());

        videoRepository.save(savedVideo);

        return videoDTO;
    }
}
