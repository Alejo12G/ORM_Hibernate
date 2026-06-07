package main.orm_hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Solo este es el ID correcto
    @Column(name = "ID_Artista")
    private int idArtista;

    @Column(name = "Nombre")    
    private String nombre;

    @Column(name = "Genero_Musical")    
    private String generoMusical;

    public Artista() {}

    public Artista(String nombre, String generoMusical) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    @Override
    public String toString() {
        return "Artista{" + "idArtista=" + idArtista + ", nombre=" + nombre + ", generoMusical=" + generoMusical + '}';
    }
}
