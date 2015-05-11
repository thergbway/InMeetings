import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.LinkedList;
import java.util.List;

public class UserManagerMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        LinkedList<User> usersToPersist = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Role adminRole = new Role();
            adminRole.setRoleName("admin" + i + System.currentTimeMillis());
            Role userRole = new Role();
            userRole.setRoleName("user" + i + System.currentTimeMillis());

            User user = new User();
            user.setRole(i % 2 == 0 ? adminRole : userRole);
            user.setLogin(i + "_login_" + System.currentTimeMillis());
            user.setPassword(i + "_pass_" + System.currentTimeMillis());
            user.setFirstName(i + "_firstName_" + System.currentTimeMillis());
            user.setLastName(i + "_lastName_" + System.currentTimeMillis());
            usersToPersist.add(user);
        }

        createAndStoreAllUsers(usersToPersist);

        List<User> users = getUsers();
        users.forEach(user -> System.out.println(user));

        closeAll();
    }

    private static void createAndStoreAllUsers(List<User> users) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        users.forEach(user -> {
            em.persist(user.getRole());
            em.persist(user);
        });
        tx.commit();
    }

    private static List<User> getUsers() {
        CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
        CriteriaQuery<User> all = cq.select(cq.from(User.class));
        return em.createQuery(all).getResultList();
    }

    private static void closeAll() {
        em.close();
        emf.close();
    }

}
