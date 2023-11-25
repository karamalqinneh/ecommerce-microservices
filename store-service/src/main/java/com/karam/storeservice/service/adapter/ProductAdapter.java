package com.karam.storeservice.service.adapter;


import com.karam.storeservice.domain.Product;
import com.karam.storeservice.web.dto.ProductDto;

public class ProductAdapter {

    public static ProductDto getDto(Product product) {
        return new ProductDto(
                product.getProductNumber(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl()
        );
    }

    public static Product getProduct(ProductDto product) {
        return new Product(
                product.getProductNumber(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl()
        );
    }
}
