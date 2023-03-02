package com.project.myshop.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.myshop.controller.dto.MemberImgResponse;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3UploadService {

    private AmazonS3Client amazonS3Client;

    private static final int THUMWIDTH = 200;
    private static final int THUMHEIGHT = 200;

    private String bucket = "sayho-myshop-bucket";

    public S3UploadService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public MemberImgResponse upload(MultipartFile multipartFile, String dirName) throws IOException {
        // origin metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        String fileName = multipartFile.getName() + UUID.randomUUID();
        String tumFileName = fileName + "Thum";
        String originImgUrl = putS3(bucket, fileName, multipartFile.getInputStream(), objectMetadata);

        BufferedImage imgResize = resizeImg(multipartFile);

        ObjectMetadata thumObjectMetadata = new ObjectMetadata();
        InputStream convertToInputStreamImg = convertToInputStream(imgResize,
                multipartFile.getContentType(),
                thumObjectMetadata);

        String thumImgUrl = putS3(bucket, tumFileName, convertToInputStreamImg, thumObjectMetadata);

        return MemberImgResponse.builder()
                .imgUrl(originImgUrl)
                .tumImgUrl(thumImgUrl)
                .build();
    }

    public String putS3(String bucket, String fileName, InputStream inputStream, ObjectMetadata objectMetadata) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));
        return amazonS3Client.getResourceUrl(bucket, fileName);
    }

    public BufferedImage resizeImg(MultipartFile multipartFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

        // 원본 이미지 크기
        int originWidth = bufferedImage.getWidth();
        int originHeight = bufferedImage.getHeight();

        // 썸네일보다 크기가 작으면 원본을 그대로 저장
        if(originHeight < THUMHEIGHT && originWidth < THUMWIDTH) {
            return bufferedImage;
        }

        BufferedImage outputImage = new BufferedImage(THUMWIDTH, THUMHEIGHT, bufferedImage.getType());

        return Scalr.resize(bufferedImage, THUMWIDTH, THUMHEIGHT);
    }

    public InputStream convertToInputStream(BufferedImage imgResize, String imageType, ObjectMetadata objectMetadata) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String fileFormatName = imageType.substring(imageType.lastIndexOf("/") + 1).toLowerCase();
        ImageIO.write(imgResize, fileFormatName, outputStream);
        byte[] buffer = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(buffer);

        objectMetadata.setContentType(imageType);
        objectMetadata.setContentLength(buffer.length);
        return inputStream;
    }
}
