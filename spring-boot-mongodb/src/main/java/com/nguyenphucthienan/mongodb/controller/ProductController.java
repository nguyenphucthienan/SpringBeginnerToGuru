package com.nguyenphucthienan.mongodb.controller;

import com.nguyenphucthienan.mongodb.command.ProductForm;
import com.nguyenphucthienan.mongodb.converter.ProductToProductForm;
import com.nguyenphucthienan.mongodb.domain.Product;
import com.nguyenphucthienan.mongodb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductToProductForm productToProductForm;

    public ProductController(ProductService productService, ProductToProductForm productToProductForm) {
        this.productService = productService;
        this.productToProductForm = productToProductForm;
    }

    @RequestMapping("/")
    public String redirectToList() {
        return "redirect:/products/list";
    }

    @RequestMapping({"/products/list", "/products"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "products/list";
    }

    @RequestMapping("/products/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "products/show";
    }

    @RequestMapping("products/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);
        model.addAttribute("productForm", productForm);
        return "products/form";
    }

    @RequestMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "products/form";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/form";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);
        return "redirect:/products/show/" + savedProduct.getId();
    }

    @RequestMapping("/products/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/products/list";
    }
}
