package main.orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMActualizar {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Cargar configuración de Hibernate
                .addAnnotatedClass(Artista.class) // Registrar la entidad
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 ID del artista que queremos actualizar
                int idArtista = 16; // Cambiar según la BD

                // 🔹 Buscar el artista en la base de datos
                Artista artista = session.get(Artista.class, idArtista);

                if (artista != null) {
                    // 🔹 Actualizar los valores
                    artista.setNombre("Henry Alfonso");
                    artista.setGeneroMusical("Bachata");

                    // 🔹 Guardar cambios
                    session.getTransaction().commit();
                    System.out.println("Artista actualizado: " + artista);
                } else {
                    System.out.println("No se encontró el artista con ID: " + idArtista);
                }

            } catch (Exception e) {
                System.err.println("Error en la actualización: " + e.getMessage());
                e.printStackTrace();
            } finally {
                factory.close(); // Cerrar la fábrica de sesiones
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

