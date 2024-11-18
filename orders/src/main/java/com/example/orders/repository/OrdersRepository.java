package com.example.orders.repository;

import com.example.orders.model.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {
}
//proporciona los emtodos crud
//long es el tipo de la clave primaria de la entidad OrdersModel.