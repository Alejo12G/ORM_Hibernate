package orm_hibernate;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import orm_hibernate.utils.Encriptar;
import orm_hibernate.utils.Rol;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String correo;

    private String telefono;
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol;

    @Column(name = "password_hash", nullable = false)
    private String contraseña;

    private Boolean activo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // Hibernate requiere un constructor vacío
    public Usuario() {
        this.activo = true;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Constructor optimizado para crear nuevos usuarios
    public Usuario(String nombre, String correo, String telefono, String direccion, Rol rol, String contraseña) {
        this(); // Llama al constructor vacío para inicializar activo y fecha
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
        this.contraseña = contraseña;
    }

    @PrePersist
    @PreUpdate
    private void antesDeGuardar() {
        if (this.contraseña != null && !this.contraseña.isEmpty()) {
            this.contraseña = Encriptar.hashPassword(this.contraseña);
        }
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %-15s | Correo: %-20s | Rol: %-7s | Activo: %b", 
                             id, nombre, correo, (rol != null ? rol.name() : "N/A"), activo);
    }
}