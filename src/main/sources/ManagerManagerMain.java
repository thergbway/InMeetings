//import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
//import com.inmeetings.persistence.dao.entities.Manager;
//import com.inmeetings.persistence.dao.entities.Meeting;
//import com.inmeetings.persistence.dao.entities.Role;
//import com.inmeetings.persistence.dao.entities.User;
//import com.inmeetings.persistence.dao.implementations.ManagerDAOImpl;
//
//import java.sql.Timestamp;
//import java.util.LinkedList;
//import java.util.List;
//
//public class ManagerManagerMain {
//
//    public static void main(String[] args) {
//        LinkedList<Manager> managersToPersist = new LinkedList<>();
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
//            Manager manager = new Manager(meeting, user, i + "_desc_" + System.currentTimeMillis());
//            managersToPersist.add(manager);
//        }
//
//        ManagerDAOImpl managerDAO = new ManagerDAOImpl();
//        managersToPersist.forEach(manager -> managerDAO.create(manager));
//
//        List<Manager> managers = managerDAO.getAllManagers();
//        managers.forEach(m -> System.out.println(m));
//
//        EntityManagerFactoryHolder.getEntityManagerFactory().close();
//    }
//}