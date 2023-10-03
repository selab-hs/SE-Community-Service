package com.core.service.image.presentation;

import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import com.core.service.image.application.S3Uploader;
import com.core.service.image.dto.response.UploadBundle;
import com.core.service.image.dto.response.UploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지 관리")
@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageRestController {

    private final S3Uploader s3Uploader;

    @Operation(summary = "이미지 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestPart(name = "image") MultipartFile multipartFile) {
        UploadBundle bundle = new UploadBundle(multipartFile, "temp");
        UploadResponse response = s3Uploader.upload(bundle);

        return ResponseDto.toResponseEntity(ResponseMessage.UPLOAD_IMAGE_SUCCESS, response);
    }
}
