package com.product.manager.product_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.manager.product_manager.model.Product;
import com.product.manager.product_manager.service.ProductService;




@RestController
@RequestMapping("/product")
//localhost:8080/product
public class ProductController {
    //POST, GET, PUT, DELETE

    //   JSON: {
    //   "nome": "",
    //   "quantity": 0,
    //   "description": "",
    //   "price": 0
    //    }

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody Product product ) {
        String message = productService.createProduct(product);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping("/{id}") //localhost:8080/product/4
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body("Produto deletado!");
    }

    @GetMapping("/filter/{name}")
    public ResponseEntity<Product> findByName(@PathVariable String name) {
        Product product = productService.findByName(name);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable int id) {
      productService.updateProduct(product, id);
      return ResponseEntity.ok().body("produto foi alterado!");
    }

    //   JSON: {
    //   "nome": "",
    //   "quantity": 0,
    //   "description": "",
    //   "price": 0
    //    }
    
}
