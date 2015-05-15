//import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
//import com.inmeetings.persistence.dao.entities.Meeting;
//import com.inmeetings.persistence.dao.implementations.MeetingDAOImpl;
//
//import java.sql.Timestamp;
//import java.util.LinkedList;
//import java.util.List;
//
//public class MeetingManagerMain {
//
//    public static void main(String[] args) {
//        LinkedList<Meeting> meetingToPersist = new LinkedList<>();
//        for (int i = 0; i < 1; i++) {
//            Meeting meeting = new Meeting();
//            meeting.setName(i + "_name_" + System.currentTimeMillis());
//            meeting.setDescription(i + "_descr_" + System.currentTimeMillis());
//            meeting.setStartTime(new Timestamp(System.currentTimeMillis() + 600_000L));
//            meeting.setEndTime(new Timestamp(System.currentTimeMillis() + 1_200_000));
//            meetingToPersist.add(meeting);
//        }
//
//        MeetingDAOImpl meetingDAO = new MeetingDAOImpl();
//        meetingToPersist.forEach(meeting -> meetingDAO.create(meeting));
//
//        List<Meeting> meetings = meetingDAO.getAllMeetings();
//        meetings.forEach(m -> System.out.println(m));
//
//        EntityManagerFactoryHolder.getEntityManagerFactory().close();
//    }
//}
