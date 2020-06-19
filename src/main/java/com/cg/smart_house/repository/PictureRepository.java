package com.cg.smart_house.repository;

import com.cg.smart_house.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture findByImageUrl(String imageUrl);
}