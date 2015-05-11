import com.inmeetings.persistence.dao.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ParticipantManagerMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        LinkedList<Participant> participantsToPersist = new LinkedList<>();
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

            Participant participant = new Participant(meeting, user);
            participantsToPersist.add(participant);
        }

        createAndStoreAllParticipants(participantsToPersist);

        List<Participant> participants = getParticipants();
        participants.forEach(p -> System.out.println(p));

        closeAll();
    }

    private static void createAndStoreAllParticipants(List<Participant> participants) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        participants.forEach(p -> {
            em.persist(p.getUser().getRole());
            em.persist(p.getUser());
            em.persist(p.getMeeting());
            em.persist(p);
        });
        tx.commit();
    }

    private static List<Participant> getParticipants() {
        CriteriaQuery<Participant> cq = em.getCriteriaBuilder().createQuery(Participant.class);
        CriteriaQuery<Participant> all = cq.select(cq.from(Participant.class));
        return em.createQuery(all).getResultList();
    }

    private static void closeAll() {
        em.close();
        emf.close();
    }

}