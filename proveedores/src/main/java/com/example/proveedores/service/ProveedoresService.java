package com.example.proveedores.service;

import com.example.proveedores.model.ProveedoresModel;
import com.example.proveedores.repository.ProveedoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedoresService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ProveedoresRepository proveedoresRepository;


    public List<ProveedoresModel> getProveedores() {
        return proveedoresRepository.findAll();
    }

    public ResponseEntity<Object> findProveedores(Long id) {
        Optional<ProveedoresModel> existingProveedoresOptional = proveedoresRepository.findById(id);
        if (existingProveedoresOptional.isPresent()) {
           ProveedoresModel existingProveedores = existingProveedoresOptional.get();
            return new ResponseEntity<>(existingProveedores, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<Object> createProveedores(ProveedoresModel proveedores) {
        proveedoresRepository.save(proveedores);
        return new ResponseEntity<>("Proveedor creado", HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProveedores(Long id, ProveedoresModel updatedProveedores) {
        Optional<ProveedoresModel> existingProveedoresOptional = proveedoresRepository.findById(id);
        if (existingProveedoresOptional.isPresent()) {
            ProveedoresModel existingProveedores = existingProveedoresOptional.get();
            existingProveedores.setName(updatedProveedores.getName());
            existingProveedores.setAddress(updatedProveedores.getAddress());
            existingProveedores.setStatus(updatedProveedores.getStatus());

            proveedoresRepository.save(existingProveedores);

            return new ResponseEntity<>("Orden actualizada", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteProveedores(Long id) {
        Optional<ProveedoresModel> existingProveedoresOptional = proveedoresRepository.findById(id);
        if (existingProveedoresOptional.isPresent()) {
            proveedoresRepository.deleteById(id);
            return new ResponseEntity<>("Orden eliminada", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }

}
