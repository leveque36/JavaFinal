package com.example.proveedores.repository;


import com.example.proveedores.model.ProveedoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<ProveedoresModel, Long> {

}