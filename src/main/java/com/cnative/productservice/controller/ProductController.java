package com.cnative.productservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.cnative.productservice.model.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ProductController {
  
  private List<Product> products = new ArrayList<>(List.of(
    new Product(1, "Laptop", "Dell Laptop"),
    new Product(2, "Mobile", "One Plus Mobile"),
    new Product(3, "Watch", "Boat Smart Watch")
  ));

  @GetMapping("/")
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(products);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product>  getProduct(@PathVariable("id") long id) {
    Optional<Product> productOptional = products.stream().filter(product -> product.getId() == id).findFirst();
    if(productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/")
  public ResponseEntity<Product>  createProduct(@RequestBody Product product) {
    products.add(product);
    return ResponseEntity.ok(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product updatedProduct) {
    Optional<Product> productOptional = products.stream().filter(product -> product.getId() == id).findFirst();
    if(productOptional.isPresent()) {
      products.add(updatedProduct);
      return ResponseEntity.ok(updatedProduct);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
    Optional<Product> productOptional = products.stream().filter(product -> product.getId() == id).findFirst();
    if(productOptional.isPresent()) {
      Product product = productOptional.get();
      products.remove(product);
    }
    return ResponseEntity.ok().build();
  }

}
