package com.example.model;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String ip;

    // Constructor, getters y setters
    public Usuario(Long id, String nombre, String email, String ip) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.ip = ip;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
}
