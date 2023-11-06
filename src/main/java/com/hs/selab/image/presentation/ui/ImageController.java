package com.hs.selab.image.presentation.ui;

import com.hs.selab.image.application.S3Uploader;
import com.hs.selab.image.dto.request.SmartEditor;
import com.hs.selab.image.dto.response.UploadBundle;
import com.hs.selab.image.dto.response.UploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Tag(name = "이미지 관리")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final S3Uploader s3Uploader;

    @Operation(summary = "이미지 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public View upload(HttpServletRequest request, ModelMap model)
        throws UnsupportedEncodingException {
        List<MultipartFile> imgFiles = new ArrayList<>();

        Map<String, MultipartFile> fileMap = new HashMap<>();
        if(request instanceof MultipartHttpServletRequest) {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;

            fileMap = req.getFileMap();
            fileMap.forEach((key, value) -> {
                imgFiles.add(value);
            });

        } else {
            model.addAttribute("result", HttpStatus.BAD_REQUEST);
        }

        if(imgFiles.size() > 0) {
            MultipartFile imgFile = imgFiles.get(0);
            try {
                UploadBundle bundle = new UploadBundle(imgFile, "temp");
                SmartEditor result = s3Uploader.singleImageUpload(bundle, imgFile);

                model.addAttribute("sFileURL", result.getSFileURL());
                model.addAttribute("sFileName", result.getSFileName());
                model.addAttribute("result", HttpStatus.OK);
            } catch(Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("result", HttpStatus.BAD_REQUEST);
            }
        } else {
            model.addAttribute("result", HttpStatus.BAD_REQUEST);
        }

        return new MappingJackson2JsonView();
    }
}
