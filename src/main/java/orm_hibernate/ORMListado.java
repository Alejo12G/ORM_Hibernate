package orm_hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ORMListado {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure() // Cargar configuración de Hibernate
                .addAnnotatedClass(Usuario.class) // Registrar la entidad Usuario
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 Obtener la lista completa de usuarios
                List<Usuario> usuarios = session.createQuery("from Usuario", Usuario.class).getResultList();

                // 🔹 Mostrar los resultados
                if (!usuarios.isEmpty()) {
                    System.out.println("Lista de Usuarios:");
                    for (Usuario usuario : usuarios) {
                        System.out.println(usuario);
                    }
                } else {
                    System.out.println("No hay usuarios registrados en la base de datos.");
                }

                session.getTransaction().commit(); // Confirmar transacción
                System.out.println("Listado finalizado correctamente");

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
