package com.example.products.service;


import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts (){
        return productRepository.findAll();
    }

    public ResponseEntity<Object> findProduct(Long id) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar el producto", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> createProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Producto creado", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setSku(updatedProduct.getSku());
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setStatus(updatedProduct.getStatus());

            productRepository.save(existingProduct);

            return new ResponseEntity<>("Producto actualizado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar el producto", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            productRepository.deleteById(id);
            return new ResponseEntity<>("Se elimino el producto correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar el producto", HttpStatus.NOT_FOUND);
        }
    }

}
