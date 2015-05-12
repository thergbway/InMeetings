import com.inmeetings.persistence.dao.EntityManagerFactoryHolder;
import com.inmeetings.persistence.dao.entities.Role;
import com.inmeetings.persistence.dao.implementations.RoleDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.LinkedList;
import java.util.List;

public class RoleManagerMain {
    public static void main(String[] args) {
        LinkedList<Role> rolesToPersist = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            Role role = new Role();
            role.setRoleName(i + "_roleName_" + System.currentTimeMillis());
            rolesToPersist.add(role);
        }

        RoleDAOImpl roleDAO = new RoleDAOImpl();
        rolesToPersist.forEach(role -> roleDAO.create(role));

        List<Role> roles = roleDAO.getAllRoles();
        roles.forEach(r -> System.out.println(r));

        EntityManagerFactoryHolder.getEntityManagerFactory().close();
    }
}
