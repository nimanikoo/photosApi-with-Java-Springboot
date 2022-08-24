package com.nikoo.photoapi.Services;

import com.nikoo.photoapi.Models.PhotosModel;
import com.nikoo.photoapi.Repository.IPhotosRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class PhotosService {
    private  final IPhotosRepository photosRepository;

    public PhotosService(IPhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }


    public Iterable<PhotosModel> getall() {

        return photosRepository.findAll();
    }


    public PhotosModel getById(Integer id) {

        return photosRepository.findById(id).orElse(null);
    }

    public  void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public PhotosModel Save(String fileName, String contentType, byte[] data) {
        PhotosModel photo=new PhotosModel();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photosRepository.save(photo);
        return photo;
    }


}
