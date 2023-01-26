package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public void getUser(String model, int series) {
      User user = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class)
              .setParameter("model", model).setParameter("series", series).getSingleResult();
      System.out.println(user.toString());
   }

}
