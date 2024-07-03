
package surnoi.FusionIQ.FusionIQ.controller;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import surnoi.FusionIQ.FusionIQ.data.VideoMetadata;
import surnoi.FusionIQ.FusionIQ.exception.UserNotFoundException;
import surnoi.FusionIQ.FusionIQ.repo.VideoMetadataRepository;
import surnoi.FusionIQ.FusionIQ.service.VideoMetadataService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/api")
public class FileUploadController {
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private VideoMetadataRepository videoMetadataRepository;
    @Autowired
    VideoMetadataService videoMetadataService;
    private final String bucketName = "fusioniq-videostorage";
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        String key = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//        try {
//            File convertedFile = convertMultiPartToFile(file);
//            amazonS3.putObject(new PutObjectRequest(bucketName, key, convertedFile) );
//            String s3Url = amazonS3.getUrl(bucketName, key).toString();  // Get the S3 URL
//            convertedFile.delete();
//            // Save metadata including the S3 URL to the database
//            VideoMetadata metadata = new VideoMetadata();
//            metadata.setFileName(file.getOriginalFilename());
//            metadata.setS3Key(key);
//            metadata.setS3Url(s3Url);  // Save the S3 URL
//            videoMetadataRepository.save(metadata);
//            return new ResponseEntity<>("File uploaded successfully, URL: " + s3Url, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    private File convertMultiPartToFile(MultipartFile file) throws IOException {
//        File convFile = new File(file.getOriginalFilename());
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//        return convFile;
//    }

//    @PostMapping("/upload/{courseId}")
//    public ResponseEntity<String> uploadFile(@PathVariable Long courseId, @RequestParam("file") MultipartFile file) {
//        String key = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//        try {
//            File convertedFile = convertMultiPartToFile(file);
//            amazonS3.putObject(new PutObjectRequest(bucketName, key, convertedFile));
//            String s3Url = amazonS3.getUrl(bucketName, key).toString();
//            convertedFile.delete();
//
//            // Create and save metadata
//            VideoMetadata metadata = new VideoMetadata();
//            metadata.setFileName(file.getOriginalFilename());
//            metadata.setS3Key(key);
//            metadata.setS3Url(s3Url);
//
//            videoMetadataService.uploadVideo(courseId, metadata);
//
//            return new ResponseEntity<>("File uploaded successfully, URL: " + s3Url, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @GetMapping("/courses/videos/{courseId}")
    public ResponseEntity<List<VideoMetadata>> getVideosByCourse(@PathVariable Long courseId) {
        List<VideoMetadata> videos = videoMetadataService.getVideosByCourse(courseId);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/videos")
    public ResponseEntity<List<VideoMetadata>> getAllVideoMetadata() {
        List<VideoMetadata> videoMetadataList = videoMetadataRepository.findAll();
        return ResponseEntity.ok(videoMetadataList);
    }
    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoMetadata> getVideoMetadata(@PathVariable Long id) {
        Optional<VideoMetadata> videoMetadata = videoMetadataRepository.findById(id);
        if (videoMetadata.isPresent()) {
            return ResponseEntity.ok(videoMetadata.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/videos/course/{courseId}")
    public ResponseEntity<List<VideoMetadata>> getVideoMetadataByCourseId(@PathVariable Long courseId) {
        try {
            List<VideoMetadata> videoMetadataList = videoMetadataService.getVideosByCourse(courseId);
            return ResponseEntity.ok(videoMetadataList);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    @PutMapping("/videos/progress/{id}")
    public ResponseEntity<String> updateVideoProgress(@PathVariable("id") Long id,
                                                      @RequestParam("watchedPercentage") String watchedPercentage,
                                                      @RequestParam("lastWatchedTime") String lastWatchedTime) {
        Optional<VideoMetadata> videoOptional = videoMetadataRepository.findById(id);
        if (videoOptional.isPresent()) {
            VideoMetadata video = videoOptional.get();
            video.setWatchedPercentage(watchedPercentage);
            video.setLastWatchedTime(lastWatchedTime);
            videoMetadataRepository.save(video);
            return new ResponseEntity<>("Progress updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/upload/{courseId}/{basicInfoId}")
    public ResponseEntity<String> uploadFile(@PathVariable Long courseId, @PathVariable Long basicInfoId, @RequestParam("file") MultipartFile file) {
        String key = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        try {
            File convertedFile = convertMultiPartToFile(file);
            amazonS3.putObject(new PutObjectRequest(bucketName, key, convertedFile));
            String s3Url = amazonS3.getUrl(bucketName, key).toString();
            convertedFile.delete();

            VideoMetadata videoMetadata = new VideoMetadata();
            videoMetadata.setFileName(file.getOriginalFilename());
            videoMetadata.setS3Key(key);
            videoMetadata.setS3Url(s3Url);

            videoMetadataService.uploadVideo(courseId, basicInfoId, videoMetadata);

            return new ResponseEntity<>("File uploaded successfully, URL: " + s3Url, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
 