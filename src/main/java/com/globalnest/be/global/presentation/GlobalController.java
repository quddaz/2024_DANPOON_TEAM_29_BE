package com.globalnest.be.global.presentation;

import com.globalnest.be.global.application.AWSStorageService;
import com.globalnest.be.global.dto.request.ImageUploadTestRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "GlobalController", description = "전체 설정을 위해서 필요한 api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/global")
public class GlobalController {

    private final AWSStorageService awsStorageService;

    @Value("${server.env}")
    private String env;

    @Operation(summary = "health check", description = "ci/cd를 위한 health check api")
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(env);
    }

    @Operation(summary = "사진 업로드 테스트", description = "사진 업로드 테스트")
    @PostMapping(value = "/upload-test", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadTest(
            @RequestPart MultipartFile file,
            @RequestPart ImageUploadTestRequest request
    ) {

        awsStorageService.uploadFile(file, request.folderName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("upload test");
    }

    @Operation(summary = "사진 삭제 테스트", description = "사진 삭제 테스트")
    @DeleteMapping("/delete-image")
    public ResponseEntity<String> deleteImage(
            @RequestParam String fileUrl
    ) {
        awsStorageService.deleteFile(fileUrl);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("delete image");
    }
}
