import com.inmeetings.persistence.dao.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoleManagerMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        LinkedList<Role> rolesToPersist = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Role role = new Role();
            role.setRoleName(i + "_roleName_" + System.currentTimeMillis());
            rolesToPersist.add(role);
        }
        createAndStoreAllRoles(rolesToPersist);

        List<Role> roles = getRoles();
        roles.forEach(r -> System.out.println(r.getId() + " -> " + r.getRoleName()));

        closeAll();
    }

    private static void createAndStoreRole(Role role) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(role);
        tx.commit();
    }

    private static void createAndStoreAllRoles(List<Role> roles) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        roles.forEach(r -> em.persist(r));
        tx.commit();
    }

    private static List<Role> getRoles() {
        return new ArrayList<>(0);
    }

    private static void closeAll() {
        em.close();
        emf.close();
    }
}
