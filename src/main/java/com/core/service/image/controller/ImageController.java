package com.core.service.image.controller;

import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import com.core.service.image.application.S3Uploader;
import com.core.service.image.dto.response.UploadBundle;
import com.core.service.image.dto.response.UploadResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final S3Uploader s3Uploader;

    @PostMapping
    public ResponseEntity<?> upload(
        @RequestParam("image") MultipartFile multipartFile)
        throws IOException {
        UploadBundle bundle = new UploadBundle(multipartFile, "temp");
        UploadResponse response = s3Uploader.upload(bundle);

        return ResponseDto.toResponseEntity(ResponseMessage.UPLOAD_IMAGE_SUCCESS, response);
    }
}