import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.entities.User;
import com.inmeetings.persistence.dao.implementations.UserDAOImpl;

import java.util.LinkedList;
import java.util.List;

public class UserManagerMain {

    public static void main(String[] args) {
        LinkedList<User> usersToPersist = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
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

        UserDAOImpl userDAO = new UserDAOImpl();
        usersToPersist.forEach(user -> userDAO.create(user));

        List<User> users = userDAO.getAllUsers();
        users.forEach(user -> System.out.println(user));

        EntityManagerFactoryHolder.getEntityManagerFactory().close();
    }
}
