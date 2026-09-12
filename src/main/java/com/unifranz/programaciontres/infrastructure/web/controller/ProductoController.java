package com.unifranz.programaciontres.infrastructure.web.controller;

import com.unifranz.programaciontres.application.dto.ProductoDto;
import com.unifranz.programaciontres.application.dto.ProductoResumenDto;
import com.unifranz.programaciontres.application.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoDto productoDto) {
        ProductoDto producto = productoService.guardar(productoDto);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResumenDto>> listar(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String marca) {
        return ResponseEntity.ok(productoService.listar(id, nombre, marca));
    }

    @GetMapping("/detalle")
    public ResponseEntity<List<ProductoDto>> listarDetalle() {
        return ResponseEntity.ok(productoService.listarDetalle());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> editar(@PathVariable Long id,
                                              @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoService.editar(id, productoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.eliminar(id));
    }

    @DeleteMapping("/{id}/fisico")
    public ResponseEntity<Void> eliminarFisico(@PathVariable Long id) {
        productoService.eliminarFisico(id);
        return ResponseEntity.noContent().build();
    }
}
