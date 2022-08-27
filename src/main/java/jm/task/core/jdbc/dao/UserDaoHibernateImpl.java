package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaDelete;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {

    }

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `users` (" +
                            "`id` BIGINT NOT NULL AUTO_INCREMENT," +
                            "`name` VARCHAR(45) NULL," +
                            "`last_name` VARCHAR(45) NULL," +
                            "`age` TINYINT NULL," +
                            "PRIMARY KEY (`id`)" +
                            ");")
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS `users`").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User deleteUser = session.get(User.class, id);
            session.remove(deleteUser);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE `users`;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public User getUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE id = :id", User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public List<User> getUsersByAgeInterval(Byte min, Byte max) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE age >= :min AND age <= :max", User.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        }
    }

    @Override
    public void updateUserName(long id, String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("UPDATE User SET name = :name WHERE id = :id")
                    .setParameter("name", name)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
