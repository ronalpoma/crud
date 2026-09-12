package com.unifranz.programaciontres.application.service.Impl;

import com.unifranz.programaciontres.application.dto.ProductoDto;
import com.unifranz.programaciontres.application.dto.ProductoResumenDto;
import com.unifranz.programaciontres.application.service.ProductoService;
import com.unifranz.programaciontres.domain.Producto;
import com.unifranz.programaciontres.infrastructure.Persistence.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public ProductoDto guardar(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setPrecio(productoDto.getPrecio());
        Producto guardado = productoRepository.save(producto);
        return dto(guardado);
    }

    @Override
    public List<ProductoResumenDto> listar(Long id, String nombre, String marca) {
        return productoRepository.findAll()
                .stream()
                .filter(p -> !p.isEliminado())
                .filter(p -> id == null || p.getId().equals(id))
                .filter(p -> nombre == null || (p.getNombre() != null
                        && p.getNombre().equalsIgnoreCase(nombre)))
                .filter(p -> marca == null || (p.getMarca() != null
                        && p.getMarca().equalsIgnoreCase(marca)))
                .map(this::resumen)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDto> listarDetalle() {
        return productoRepository.findAll()
                .stream()
                .map(this::dto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDto editar(Long id, ProductoDto productoDto) {
        Producto producto = buscar(id);
        if (producto.isEliminado()) {
            throw new RuntimeException("No se puede editar un producto eliminado");
        }
        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setPrecio(productoDto.getPrecio());
        return dto(productoRepository.save(producto));
    }

    @Override
    public ProductoDto eliminar(Long id) {
        Producto producto = buscar(id);
        producto.setEliminado(true);
        return dto(productoRepository.save(producto));
    }

    @Override
    public void eliminarFisico(Long id) {
        Producto producto = buscar(id);
        productoRepository.delete(producto);
    }

    private Producto buscar(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No existe un producto con el id " + id));
    }

    private ProductoResumenDto resumen(Producto producto) {
        return new ProductoResumenDto(producto.getId(), producto.getNombre(),
                producto.getMarca(), producto.getPrecio());
    }

    private ProductoDto dto(Producto producto) {
        return new ProductoDto(producto.getId(), producto.getNombre(), producto.getMarca(),
                producto.getPrecio(), producto.isEliminado());
    }
}
