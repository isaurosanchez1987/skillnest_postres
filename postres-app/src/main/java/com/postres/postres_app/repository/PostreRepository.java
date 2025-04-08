package com.postres.postres_app.repository;

import com.postres.postres_app.entity.Postre;
import com.postres.postres_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostreRepository extends JpaRepository<Postre, Long> {
    List<Postre> findByUsuario(User usuario);
    List<Postre> findByTituloContainingIgnoreCase(String titulo);
}
