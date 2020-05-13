package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository extends CrudRepository<Image, Long> {

}
