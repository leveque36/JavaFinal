package com.example.proveedores.controller;

import com.example.proveedores.model.ProveedoresModel;
import com.example.proveedores.service.ProveedoresService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/proveedores")
@RequiredArgsConstructor
public class ProveedoresController {

    private final ProveedoresService proveedoresService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProveedoresModel> getProveedores() {
        return this.proveedoresService.getProveedores();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createProveedores(@Valid @RequestBody ProveedoresModel proveedores, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return proveedoresService.createProveedores(proveedores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProveedores(@PathVariable("id") Long id, @RequestBody ProveedoresModel updatedProveedores) {
        return proveedoresService.updateProveedores(id, updatedProveedores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProveedores(@PathVariable("id") Long id) {
        return proveedoresService.deleteProveedores(id);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdProveedores(@PathVariable("id") Long id) {
        return proveedoresService.findProveedores(id);
    }
}
