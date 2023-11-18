package com.hs.selab.image.presentation.ui;

import com.hs.selab.image.application.S3Uploader;
import com.hs.selab.image.dto.request.SmartEditor;
import com.hs.selab.image.dto.response.PhotoVo;
import com.hs.selab.image.dto.response.UploadBundle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Tag(name = "이미지 관리")
@RequiredArgsConstructor
@RestController
public class ImageController {

    private final S3Uploader s3Uploader;

    @Operation(summary = "이미지 업로드")
    @PostMapping("/images")
    public String upload(
        HttpServletRequest request,
        PhotoVo photoVo
    ) {

        String callback = photoVo.getCallback();
        String callback_func = photoVo.getCallback_func();
        List<MultipartFile> imgFiles = new ArrayList<>();

        Map<String, MultipartFile> fileMap = new HashMap<>();
        if (request instanceof MultipartHttpServletRequest) {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            fileMap = req.getFileMap();
            fileMap.forEach((key, value) -> {
                imgFiles.add(value);
            });

            MultipartFile imgFile = imgFiles.get(0);
            UploadBundle bundle = new UploadBundle(imgFile, "temp");
            SmartEditor result = s3Uploader.singleImageUpload(bundle, imgFile);
            //return ResponseDto.toResponseEntity(ResponseMessage.UPLOAD_IMAGE_SUCCESS, result);

            return "redirect:" + callback + "?callback_func="+callback_func+result.getSFileURL();
        }
        return null;
    }
}
