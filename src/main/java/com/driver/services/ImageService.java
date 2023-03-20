package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog



        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);

        //imageRepository2.save(image);
        blogRepository2.save(blog);

        return image;

    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();
        String dim = image.getDimensions();

        String[] arr = dim.split("X");
        String[] screen = screenDimensions.split("X");

        int area = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);

        int givenarea = Integer.parseInt(screen[0]) * Integer.parseInt(screen[1]);

        return area/givenarea;

    }
}
