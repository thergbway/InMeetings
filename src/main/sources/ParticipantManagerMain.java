//import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
//import com.inmeetings.persistence.dao.entities.Meeting;
//import com.inmeetings.persistence.dao.entities.Participant;
//import com.inmeetings.persistence.dao.entities.Role;
//import com.inmeetings.persistence.dao.entities.User;
//import com.inmeetings.persistence.dao.implementations.ParticipantDAOImpl;
//
//import java.sql.Timestamp;
//import java.util.LinkedList;
//import java.util.List;
//
//public class ParticipantManagerMain {
//
//    public static void main(String[] args) {
//        LinkedList<Participant> participantsToPersist = new LinkedList<>();
//        for (int i = 0; i < 2; i++) {
//            Meeting meeting = new Meeting();
//            meeting.setName(i + "_name_" + System.currentTimeMillis());
//            meeting.setDescription(i + "_descr_" + System.currentTimeMillis());
//            meeting.setStartTime(new Timestamp(System.currentTimeMillis() + 600_000L));
//            meeting.setEndTime(new Timestamp(System.currentTimeMillis() + 1_200_000));
//
//            Role adminRole = new Role("admin" + i + System.currentTimeMillis());
//            Role userRole = new Role("user" + i + System.currentTimeMillis());
//
//            User user = new User();
//            user.setRole(i % 2 == 0 ? adminRole : userRole);
//            user.setLogin(i + "_login_" + System.currentTimeMillis());
//            user.setPassword(i + "_pass_" + System.currentTimeMillis());
//            user.setFirstName(i + "_firstName_" + System.currentTimeMillis());
//            user.setLastName(i + "_lastName_" + System.currentTimeMillis());
//
//            Participant participant = new Participant(meeting, user);
//            participantsToPersist.add(participant);
//        }
//
//        ParticipantDAOImpl participantDAO = new ParticipantDAOImpl();
//        participantsToPersist.forEach(participant -> participantDAO.create(participant));
//
//        List<Participant> participants = participantDAO.getAllParticipants();
//        participants.forEach(p -> System.out.println(p));
//
//        EntityManagerFactoryHolder.getEntityManagerFactory().close();
//    }
//}