package com.product.manager.product_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.manager.product_manager.model.Product;
import com.product.manager.product_manager.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //CRUD

    //CREATE
    public String createProduct(Product product) {

        if(product.getName() == null || product.getName().isBlank()) {
            return "Preencha o nome do produto!";
        }

        if(product.getQuantity() <= 0) { //0
            return "Forneça a quantidade!";
        }

        if(product.getPrice() == null || product.getPrice() <= 0) {
            return  "Preencha o preço do produto!";
        }

       Product newProduct = new Product();
       newProduct.setName(product.getName());
       newProduct.setDescription(product.getDescription());
       newProduct.setPrice(product.getPrice());
       newProduct.setQuantity(product.getQuantity());
  
       productRepository.save(newProduct);

       return "Produto adicionado com sucesso!";
    }

    //READ
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    private Product findById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não foi encontrado!"));
    }

    //Update
    public void updateProduct(Product product, int id) {
        Product oldProduct = findById(id);

        if(product.getName() != null && !product.getName().isBlank()) {
            oldProduct.setName(product.getName());
        }

        if(product.getQuantity() > 0) { //
            oldProduct.setQuantity(product.getQuantity());
        }

        if(product.getPrice() != null && product.getPrice() > 0) {
            oldProduct.setPrice(product.getPrice());
        }

        if(product.getDescription() != null && !product.getDescription().isBlank()) {
            oldProduct.setDescription(product.getDescription());
        }

        productRepository.save(oldProduct);
    }

    //   JSON: {
    //   "nome": "DORITOS",
    //   "quantity": 20,
    //   "description": "É DEMAIS, NEGO",
    //   "price": 20
    //    }

    //   JSON: {
    //   "nome": ,
    //   "quantity": 10,
    //   "description": "É MUITO BOM, NEGO",
    //   "price":
    //    }

    //Delete
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    //READ
    //FILTRO
    public Product findByName(String name) {
        Product productFounded = productRepository.findByName(name);
        log.info("Nome do produto: {}, Produto encontrado: {}", name, productFounded.getPrice());
        return productFounded;
    }

    // tratar dados que vem do front
}
