package com.lecoingamer.services;

import com.lecoingamer.model.ImageModel;
import com.lecoingamer.model.Produit;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageServices {


    ImageModel findById(int id);
    ImageModel findByName(String name);
    ImageModel findByProduit(Produit produit);
    void SaveImage(ImageModel image);
    void deleteImage(int id);
    public ImageModel createImage(String path)throws IOException ;
    public String getImageNameAndFormat(String path);
    public String byteToString(byte[] img);
    public String getImage(String path) throws IOException;
    public String createImage(MultipartFile file) throws IOException;
}
