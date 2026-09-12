package com.unifranz.programaciontres.infrastructure.Persistence;

import com.unifranz.programaciontres.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
