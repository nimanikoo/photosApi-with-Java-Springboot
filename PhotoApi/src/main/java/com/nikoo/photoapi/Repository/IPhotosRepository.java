package com.nikoo.photoapi.Repository;

import com.nikoo.photoapi.Models.PhotosModel;
import org.springframework.data.repository.CrudRepository;

public interface IPhotosRepository extends CrudRepository<PhotosModel,Integer> {
}
