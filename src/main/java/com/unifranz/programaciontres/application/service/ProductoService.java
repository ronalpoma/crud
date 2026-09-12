package com.unifranz.programaciontres.application.service;

import com.unifranz.programaciontres.application.dto.ProductoDto;
import com.unifranz.programaciontres.application.dto.ProductoResumenDto;

import java.util.List;

public interface ProductoService {
    ProductoDto guardar(ProductoDto productoDto);
    List<ProductoResumenDto> listar(Long id, String nombre, String marca);
    List<ProductoDto> listarDetalle();
    ProductoDto editar(Long id, ProductoDto productoDto);
    ProductoDto eliminar(Long id);
    void eliminarFisico(Long id);
}
