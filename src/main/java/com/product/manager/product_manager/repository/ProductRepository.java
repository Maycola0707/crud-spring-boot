package com.product.manager.product_manager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.manager.product_manager.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
     Product findByName(String name);

    //Faz a conexão com as operações do banco de dados
}
