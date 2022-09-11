package com.dmilut.youtubeclone.service;

import com.dmilut.youtubeclone.dto.UploadVideoResponse;
import com.dmilut.youtubeclone.dto.VideoDTO;
import com.dmilut.youtubeclone.model.Video;
import com.dmilut.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        Video video = new Video();
        video.setVideoUrl(videoUrl);

        Video savedVideo = videoRepository.save(video);

        return new UploadVideoResponse(savedVideo.getId(), videoUrl);
    }

    public VideoDTO editVideo(VideoDTO videoDTO) {
        Video savedVideo = getVideoById(videoDTO.getId());

        savedVideo.setTitle(videoDTO.getTitle());
        savedVideo.setDescription(videoDTO.getDescription());
        savedVideo.setTags(videoDTO.getTags());
        savedVideo.setVideoStatus(videoDTO.getVideoStatus());
        savedVideo.setThumbnailUrl(videoDTO.getThumbnailUrl());

        videoRepository.save(savedVideo);

        return videoDTO;
    }

    public String uploadThumbnail(MultipartFile multipartFile, String videoId) {
        Video savedVideo = getVideoById(videoId);

        String thumbnailURL = s3Service.uploadFile(multipartFile);
        savedVideo.setThumbnailUrl(thumbnailURL);

        videoRepository.save(savedVideo);

        return thumbnailURL;
    }

    private Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id " + videoId));
    }

    public VideoDTO getVideoDetails(String videoId) {
        Video savedVideo = getVideoById(videoId);

        VideoDTO videoDto = new VideoDTO();
        videoDto.setVideoUrl(savedVideo.getVideoUrl());
        videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
        videoDto.setId(savedVideo.getId());
        videoDto.setTitle(savedVideo.getTitle());
        videoDto.setDescription(savedVideo.getDescription());
        videoDto.setTags(savedVideo.getTags());
        videoDto.setVideoStatus(savedVideo.getVideoStatus());

        return videoDto;
    }
}
