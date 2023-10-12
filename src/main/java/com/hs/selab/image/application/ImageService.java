package com.hs.selab.image.application;

import com.hs.selab.image.domain.ImageLog;
import com.hs.selab.image.dto.response.UploadBundle;
import com.hs.selab.image.dto.response.UploadResponse;
import com.hs.selab.image.infrastructure.ImageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageLogRepository imageLogRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public UploadResponse upload(UploadBundle bundle) {
        var imageResponse = s3Uploader.upload(bundle);

        imageLogRepository.save(
                ImageLog.builder()
                        .imageUrl(imageResponse.getUploadImageUrl())
                        .directoryPath(bundle.getDirectoryName())
                        .build()
        );

        return imageResponse;
    }
}
