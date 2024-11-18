package com.example.orders.service;

import com.example.orders.model.OrdersModel;
import com.example.orders.repository.OrdersRepository;
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
public class OrdersService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final OrdersRepository ordersRepository;


    public List<OrdersModel> getOrders() {
        return ordersRepository.findAll();
    }

    public ResponseEntity<Object> findOrder(Long id) {
        Optional<OrdersModel> existingOrderOptional = ordersRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            OrdersModel existingOrder = existingOrderOptional.get();
            return new ResponseEntity<>(existingOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> createOrder(OrdersModel order) {
        Long productIds = order.getProductIds();
        String url = "http://localhost:8080/api/products/" + productIds;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ordersRepository.save(order);
                return new ResponseEntity<>("Orden creadacorrectamente", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("No se pudo encontrar el producto", HttpStatus.NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound ex) {
            return new ResponseEntity<>("No se pudo encontrar el producto", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> updateOrder(Long id, OrdersModel updatedOrder) {
        Optional<OrdersModel> existingOrderOptional = ordersRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            OrdersModel existingOrder = existingOrderOptional.get();
            existingOrder.setProductIds(updatedOrder.getProductIds());
            existingOrder.setOrderName(updatedOrder.getOrderName());
            existingOrder.setTotalAcum(updatedOrder.getTotalAcum());
            existingOrder.setStatus(updatedOrder.getStatus());

            ordersRepository.save(existingOrder);

            return new ResponseEntity<>("Orden actualizada", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteOrder(Long id) {
        Optional<OrdersModel> existingOrderOptional = ordersRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            ordersRepository.deleteById(id);
            return new ResponseEntity<>("Orden eliminada", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar la orden", HttpStatus.NOT_FOUND);
        }
    }

}
