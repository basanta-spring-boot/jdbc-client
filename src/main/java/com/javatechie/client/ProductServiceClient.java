package com.javatechie.client;

import com.javatechie.dto.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ProductServiceClient {


    private final RestClient restClient;

    public ProductServiceClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:9191")
                .build();
    }

    public Product addNewProduct(Product product) {
        return restClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }

    public List<Product> getAllProducts() {
        return restClient.get()
                .uri("/products")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {
                });
    }

    public Product getProduct(int id) {
        return restClient.get()
                .uri("/products/find/{id}", id)
                .retrieve()
                .body(Product.class);
    }

    public Product updateProduct(Integer id, Product product) {
        return restClient.put()
                .uri("/products/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }


    public void deleteProduct(Integer id) {
        restClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}
