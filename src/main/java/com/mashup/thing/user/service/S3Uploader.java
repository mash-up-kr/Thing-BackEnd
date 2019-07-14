package com.mashup.thing.user.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mashup.thing.exception.s3.CovertFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    @Value(value = "${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String uid) {
        try {
            File uploadFile = convert(multipartFile)
                    .orElseThrow(CovertFailException::new);
            return upload(uploadFile, uid);
        } catch (IOException e) {
            throw new CovertFailException();
        }
    }

    //ToDo 프로필 삭제시 파일 삭제 추후 어떻게 처리할지 정하기
    public void remove(String fileName) {
        amazonS3Client.deleteObject(bucket, fileName);
    }

    private String upload(File uploadFile, String uid) {
        String fileName = "profile/" + uid +"_" +System.currentTimeMillis() + "_" + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

}
