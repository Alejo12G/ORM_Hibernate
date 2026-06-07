package main.orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMConsultaId {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Cargar configuración
                .addAnnotatedClass(Artista.class) // Registrar la entidad
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 ID del artista a consultar (DEBE SER UN ENTERO)
                int idArtista = 16; // Cambiar según la BD

                // 🔹 Obtener el artista desde la BD
                Artista artista = session.get(Artista.class, idArtista);

                // 🔹 Mostrar el resultado
                if (artista != null) {
                    System.out.println("Registro obtenido: " + artista);
                } else {
                    System.out.println("No se encontró el artista con ID: " + idArtista);
                }

                session.getTransaction().commit(); // Confirmar transacción
                System.out.println("Consulta finalizada correctamente");

            } catch (Exception e) {
                System.err.println("Error en la consulta: " + e.getMessage());
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
