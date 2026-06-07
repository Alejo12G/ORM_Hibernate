package orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMConsultaId {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure() // Cargar configuración
                .addAnnotatedClass(Usuario.class) // Registrar la entidad Usuario
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 ID del usuario a consultar
                int idUsuario = 1; 

                // 🔹 Obtener el usuario desde la BD
                Usuario usuario = session.get(Usuario.class, idUsuario);

                // 🔹 Mostrar el resultado
                if (usuario != null) {
                    System.out.println("Registro obtenido: " + usuario);
                } else {
                    System.out.println("No se encontró el usuario con ID: " + idUsuario);
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
