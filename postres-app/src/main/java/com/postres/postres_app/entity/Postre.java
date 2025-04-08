package com.postres.postres_app.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "postres")
public class Postre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 1000)
    private String ingredientes;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToMany(mappedBy = "postresQueLeGustan")
    private List<User> usuariosQueLesGusta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public List<User> getUsuariosQueLesGusta() {
        return usuariosQueLesGusta;
    }

    public void setUsuariosQueLesGusta(List<User> usuariosQueLesGusta) {
        this.usuariosQueLesGusta = usuariosQueLesGusta;
    }
}
