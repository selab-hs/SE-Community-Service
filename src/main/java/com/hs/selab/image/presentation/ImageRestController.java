package com.hs.selab.image.presentation;

import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import com.hs.selab.image.application.S3Uploader;
import com.hs.selab.image.dto.response.UploadBundle;
import com.hs.selab.image.dto.response.UploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> upload(@RequestPart(name = "file") MultipartFile multipartFile) {
        UploadBundle bundle = new UploadBundle(multipartFile, "temp");
        UploadResponse response = s3Uploader.upload(bundle);

        return ResponseDto.toResponseEntity(ResponseMessage.UPLOAD_IMAGE_SUCCESS, response);
    }
}
// model.addAttribute("sFileURL", response.getUploadImageUrl());
//                model.addAttribute("sFileName", bundle.getDirectoryName());