package com.nikoo.photoapi.Controllers;

import com.nikoo.photoapi.Models.PhotosModel;
import com.nikoo.photoapi.Services.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {
    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> Download(@PathVariable Integer id){
        PhotosModel photo= photosService.getById(id);
        if(photo==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        byte[] data = photo.getData();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build= ContentDisposition.builder("attachment")
                .filename(photo.getFileName())
                .build();

        return new ResponseEntity<>(data,headers, HttpStatus.OK);
    }

}
