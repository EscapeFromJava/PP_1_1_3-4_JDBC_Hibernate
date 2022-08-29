package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
            session.remove(session.get(User.class, id));
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
    public void generateRandomUsers(int n) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Stream.generate(User::getRandomUser)
                    .limit(n)
                    .forEach(session::saveOrUpdate);
            session.getTransaction().commit();
        }
    }

    @Override
    public User getUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("lastName"), lastName))
                    .getOrderList();
            return session.createQuery(criteriaQuery).getResultList();

//            return session.createQuery("FROM User WHERE lastName = :lastName ", User.class)
//                    .setParameter("lastName", lastName)
//                    .getResultList();
        }
    }

    @Override
    public List<User> getUsersByAgeInterval(Byte min, Byte max) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.between(root.get("age"), min, max))
                    .getOrderList();
            return session.createQuery(criteriaQuery).getResultList();

//            return session.createQuery("FROM User WHERE age BETWEEN :min AND :max", User.class)
//                    .setParameter("min", min)
//                    .setParameter("max", max)
//                    .getResultList();
        }
    }

    @Override
    public double getAverageAgeValue() {
        try (Session session = sessionFactory.openSession()) {
            return (double) session.createQuery("SELECT AVG(age) FROM User").getSingleResult();
        }
    }

    @Override
    public void updateUserName(long id, String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.get(User.class, id).setName(name);
            session.getTransaction().commit();
        }
    }
}
