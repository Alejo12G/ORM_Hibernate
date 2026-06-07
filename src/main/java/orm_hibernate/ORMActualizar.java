package orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import orm_hibernate.utils.Rol;

public class ORMActualizar {

    public static void main(String[] args) {
        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure() // Cargar configuración de Hibernate
                .addAnnotatedClass(Usuario.class) // Registrar la entidad Usuario
                .buildSessionFactory()) {

            // Iniciar sesión
            try (Session session = factory.openSession()) {
                session.beginTransaction(); // Iniciar transacción

                // 🔹 ID del usuario que queremos actualizar
                int idUsuario = 1; // Cambiar según la base de datos

                // 🔹 Buscar el usuario en la base de datos
                Usuario usuario = session.get(Usuario.class, idUsuario);

                if (usuario != null) {
                    // 🔹 Actualizar los valores
                    usuario.setNombre("Alejandro Gg");
                    usuario.setDireccion("LLano BBolívar 456");
                    usuario.setRol(Rol.cliente);
                    usuario.setContraseña("nuevaClave123");

                    // 🔹 Guardar cambios
                    session.getTransaction().commit();
                    System.out.println("Usuario actualizado correctamente: " + usuario);
                } else {
                    System.out.println("No se encontró el usuario con ID: " + idUsuario);
                }

            } catch (Exception e) {
                System.err.println("Error en la actualización: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
