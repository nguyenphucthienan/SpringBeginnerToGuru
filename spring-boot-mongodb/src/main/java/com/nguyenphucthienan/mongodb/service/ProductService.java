package com.nguyenphucthienan.mongodb.service;

import com.nguyenphucthienan.mongodb.command.ProductForm;
import com.nguyenphucthienan.mongodb.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAll();

    Product getById(String id);

    Product saveOrUpdate(Product product);

    void delete(String id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
