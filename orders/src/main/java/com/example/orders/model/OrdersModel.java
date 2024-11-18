package com.example.orders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo requerido")
    private Long productIds;

    @NotBlank(message = "Campo requerido")
    private String orderName;

    @NotNull(message = "Campo requerido")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a cero")
    private Double totalAcum;

    private Boolean status;
}

