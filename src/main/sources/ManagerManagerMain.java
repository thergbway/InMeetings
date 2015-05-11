import com.inmeetings.persistence.dao.entities.Manager;
import com.inmeetings.persistence.dao.entities.Meeting;
import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ManagerManagerMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        LinkedList<Manager> managersToPersist = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            Meeting meeting = new Meeting();
            meeting.setName(i + "_name_" + System.currentTimeMillis());
            meeting.setDescription(i + "_descr_" + System.currentTimeMillis());
            meeting.setStartTime(new Timestamp(System.currentTimeMillis() + 600_000L));
            meeting.setEndTime(new Timestamp(System.currentTimeMillis() + 1_200_000));

            Role adminRole = new Role("admin" + i + System.currentTimeMillis());
            Role userRole = new Role("user" + i + System.currentTimeMillis());

            User user = new User();
            user.setRole(i % 2 == 0 ? adminRole : userRole);
            user.setLogin(i + "_login_" + System.currentTimeMillis());
            user.setPassword(i + "_pass_" + System.currentTimeMillis());
            user.setFirstName(i + "_firstName_" + System.currentTimeMillis());
            user.setLastName(i + "_lastName_" + System.currentTimeMillis());

            Manager manager = new Manager(meeting, user, i + "_desc_" + System.currentTimeMillis());
            managersToPersist.add(manager);
        }

        createAndStoreAllManagers(managersToPersist);

        List<Manager> managers = getManagers();
        managers.forEach(m -> System.out.println(m));

        closeAll();
    }

    private static void createAndStoreAllManagers(List<Manager> managers) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        managers.forEach(m -> {
            em.persist(m.getUser().getRole());
            em.persist(m.getUser());
            em.persist(m.getMeeting());
            em.persist(m);
        });
        tx.commit();
    }

    private static List<Manager> getManagers() {
        CriteriaQuery<Manager> cq = em.getCriteriaBuilder().createQuery(Manager.class);
        CriteriaQuery<Manager> all = cq.select(cq.from(Manager.class));
        return em.createQuery(all).getResultList();
    }

    private static void closeAll() {
        em.close();
        emf.close();
    }

}