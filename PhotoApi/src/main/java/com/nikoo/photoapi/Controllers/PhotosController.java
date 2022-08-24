package com.nikoo.photoapi.Controllers;

import com.nikoo.photoapi.Models.PhotosModel;
import com.nikoo.photoapi.Services.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotosController {
    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }


    @GetMapping("/photos")
    public Iterable<PhotosModel> getAll(){
        return photosService.getall();
    }

    @GetMapping("/photos/{id}")
    public PhotosModel getId(@PathVariable Integer id){
        PhotosModel photo = photosService.getById(id);
        if (photo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void Delete(@PathVariable Integer id){
        photosService.remove(id);
    }

    @PostMapping("/photos/")
    public PhotosModel Create(@RequestPart("data") MultipartFile file) throws IOException {
        return photosService.Save(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    }
}
