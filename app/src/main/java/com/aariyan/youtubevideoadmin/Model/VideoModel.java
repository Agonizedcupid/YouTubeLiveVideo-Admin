package com.aariyan.youtubevideoadmin.Model;

public class VideoModel implements Comparable<VideoModel> {

    private String id,videoDate,videoDescription,videoName,videoThumbnail,videoUrl,type;
    public VideoModel(){}

    public VideoModel(String id, String videoDate, String videoDescription, String videoName, String videoThumbnail, String videoUrl,String type) {
        this.id = id;
        this.videoDate = videoDate;
        this.videoDescription = videoDescription;
        this.videoName = videoName;
        this.videoThumbnail = videoThumbnail;
        this.videoUrl = videoUrl;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoDate() {
        return videoDate;
    }

    public void setVideoDate(String videoDate) {
        this.videoDate = videoDate;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int compareTo(VideoModel videoModel) {
        return getVideoDate().compareTo(videoModel.getVideoDate());
    }
}
