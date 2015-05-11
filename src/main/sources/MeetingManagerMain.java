import com.inmeetings.persistence.dao.entities.Meeting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class MeetingManagerMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("inmeetings-main");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        LinkedList<Meeting> meetingToPersist = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Meeting meeting = new Meeting();
            meeting.setName(i + "_name_" + System.currentTimeMillis());
            meeting.setDescription(i + "_descr_" + System.currentTimeMillis());
            meeting.setStartTime(new Timestamp(System.currentTimeMillis() + 600_000L));
            meeting.setEndTime(new Timestamp(System.currentTimeMillis() + 1_200_000));
            meetingToPersist.add(meeting);
        }

        createAndStoreAllMeetings(meetingToPersist);

        List<Meeting> meetings = getMeetings();
        meetings.forEach(m -> System.out.println(m));

        closeAll();
    }

    private static void createAndStoreAllMeetings(List<Meeting> meetings) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        meetings.forEach(m -> em.persist(m));
        tx.commit();
    }

    private static List<Meeting> getMeetings() {
        CriteriaQuery<Meeting> cq = em.getCriteriaBuilder().createQuery(Meeting.class);
        CriteriaQuery<Meeting> all = cq.select(cq.from(Meeting.class));
        return em.createQuery(all).getResultList();
    }

    private static void closeAll() {
        em.close();
        emf.close();
    }
}
