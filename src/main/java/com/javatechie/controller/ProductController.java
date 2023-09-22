package com.javatechie.controller;

import com.javatechie.client.ProductServiceClient;
import com.javatechie.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-client/products")
public class ProductController {

    @Autowired
    private ProductServiceClient productServiceClient;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productServiceClient.addNewProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productServiceClient.getAllProducts();
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable int id) throws Exception {
        return productServiceClient.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) throws Exception {
        return productServiceClient.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productServiceClient.deleteProduct(id);
        return "Product removed " + id;
    }

}
