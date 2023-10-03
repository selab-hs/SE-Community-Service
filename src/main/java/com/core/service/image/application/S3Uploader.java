package com.core.service.image.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.core.service.config.s3.S3Config;
import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.board.FileConverterException;
import com.core.service.image.dto.response.UploadBundle;
import com.core.service.image.dto.response.UploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;
    private static final Logger log = LoggerFactory.getLogger(S3Uploader.class);

    private static final String URL_SLASH = "/";
    private static final String DIRECTORY_PROPERTY = "user.dir";

    @SneakyThrows
    public UploadResponse upload(UploadBundle bundle) {
        File uploadFile = convert(bundle.getMultipartFile())
                .orElseThrow(
                        () -> new FileConverterException(ErrorMessage.FAILURE_FILE_CONVERT)
                );
        String uploadImageUrl = upload(uploadFile, bundle.getDirectoryName());

        return new UploadResponse(uploadImageUrl);
    }

    public String upload(File uploadFile, String dirName) {
        String fileName = dirName + URL_SLASH + UUID.randomUUID();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    public String putS3(File uploadFile, String fileName) {

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(s3Config.bucket(), fileName,
                    uploadFile);
            amazonS3.putObject(
                    putObjectRequest.withCannedAcl(
                            CannedAccessControlList.PublicRead)
            );
        } finally {
            removeNewFile(uploadFile);
        }

        return amazonS3.getUrl(s3Config.bucket(), fileName).toString();
    }

    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
        } else {
            log.warn("File delete fail");
        }
    }

    public Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(
                System.getProperty(DIRECTORY_PROPERTY) + URL_SLASH + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
