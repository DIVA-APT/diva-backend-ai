package com.apt.diva_ai.domain.s3.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class ImageUtils {

    /**
     * Base64 이미지를 바이너리(InputStream) 형태로 변환
     * @param base64Image Base64로 인코딩 된 이미지 문자열
     * @return InputStream
     */
    public InputStream base64ToInputStream(String base64Image) {
        String base64Data = base64Image.split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        return new ByteArrayInputStream(imageBytes);
    }

    /**
     * 바이너리 형태 이미지를 InputStream으로 변환
     * @param imageBytes 이미지 바이너리 데이터
     * @return InputStream
     */
    public InputStream bytesToInputStream(byte[] imageBytes) {
        return new ByteArrayInputStream(imageBytes);
    }
}
