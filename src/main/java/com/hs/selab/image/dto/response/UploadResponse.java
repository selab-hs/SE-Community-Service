package com.hs.selab.image.dto.response;

public class UploadResponse {
    private final String uploadImageUrl;

    public UploadResponse(String uploadImageUrl) {
        this.uploadImageUrl = uploadImageUrl;
    }

    public String getUploadImageUrl() {
        return uploadImageUrl;
    }
}
