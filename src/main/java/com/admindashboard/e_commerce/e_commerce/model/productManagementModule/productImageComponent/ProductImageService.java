package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductImageRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductImageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProductImageService {
    @Autowired
    UserRepository userRepository;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.poster}")
    private String path;

    private final ProductImageRepository productImageRepository;

    private final FileService fileService;

    public ProductImageService(ProductImageRepository productImageRepository, FileService fileService, FileService fileService1) {
        this.productImageRepository = productImageRepository;
        this.fileService = fileService1;
    }

    public ProductImageResponse addImage(ProductImageRequest request, MultipartFile file) throws IOException {

        Optional<User> user1 = userRepository.findByUserName(request.getUserName());
        if (user1.isEmpty()) {
            throw new EntityNotFoundException("user name is not found.");
        }

        //1. upload the file
        String uploadedFileName = fileService.uploadFile(path,file);


        //2. set the value of poster as fileName
        request.setPosterName(uploadedFileName);

        //3. generate the poster url
        String posterUrl = baseUrl + "/file/" + uploadedFileName;


        //4. map image request dto to  product image object;
        var imageOb = ProductImage.builder()
                .imageName(request.getImageName())
                .tenantId(user1.get().getTenantId())
                .poster(request.getPosterName())
                .imageUrl(posterUrl)
                .build();

        //5. save image object
        imageOb = productImageRepository.save(imageOb);

        //6. map imageOb to image response dto
        return ProductImageResponse.builder()
                .imageName(imageOb.getImageName())
                .isThumbnailImage(imageOb.getIsThumbnailImage())
                .poster(imageOb.getPoster())
                .imageId(imageOb.getId())
                .imageUrl(imageOb.getImageUrl())
                .build();

    }

    public ProductImageResponse getImage(String imageId) {

        ProductImage imageOb=  productImageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("image is not found."));
        System.out.println(imageOb);

        String posterUrl = baseUrl + "/file/" + imageOb.getPoster();

        return ProductImageResponse.builder()
                .imageName(imageOb.getImageName())
                .isThumbnailImage(imageOb.getIsThumbnailImage())
                .poster(imageOb.getPoster())
                .imageId(imageOb.getId())
                .imageUrl(imageOb.getImageUrl())
                .build();

    }
}
