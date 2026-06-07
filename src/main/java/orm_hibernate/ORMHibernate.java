package orm_hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import orm_hibernate.utils.Rol;

public class ORMHibernate {

    public static void main(String[] args) {

        // Crear SessionFactory
        try (SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory()) {
            // Iniciar sesión            
            try (Session session = factory.openSession()) {

                session.beginTransaction();//Se da comienzo a la transacción
                
                // Crear objeto Usuario
                Usuario usuario = new Usuario("Alejandro", "ale@gmail.com", "310539487", "CR 123 #20-10", Rol.administrador, "pass1234");

                // Guardar en la base de datos
                session.save(usuario);

                // Confirmar transacción
                session.getTransaction().commit();

                System.out.println("El registro fue almacenado correctamente");
            } catch (Exception e) {
                System.err.println("Error en la transacción: " + e.getMessage());
                e.printStackTrace();
            } finally {
                factory.close();
            }

        } catch (Exception e) {
            System.err.println("Error al inicializar Hibernate: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
