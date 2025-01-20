package com.apt.diva_ai.domain.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.apt.diva_ai.domain.s3.util.ImageUtils;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 amazonS3;
    private final ImageUtils imageUtils;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public String upload(String imageUrl) {

        ResponseEntity<byte[]> imageEntity = imageUtils.downloadImageToObject(imageUrl);

        InputStream imageBytes = imageUtils.bytesToInputStream(imageEntity.getBody());
        String imageType = imageEntity.getHeaders().getContentType().toString();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageEntity.getBody().length);
        metadata.setContentType(imageType);

        String uuid = UUID.randomUUID().toString();

        String fileName = uuid + "." + imageType.split("/")[1];

        // 업로드
        amazonS3.putObject(
            new PutObjectRequest(bucketName, fileName, imageBytes,
                metadata));

        return amazonS3.getUrl(bucketName, fileName).toString();
    }
}
