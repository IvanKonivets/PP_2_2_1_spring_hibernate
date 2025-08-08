package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("select p from User p join fetch p.car");
      return query.getResultList();
   }

    @Override
    public User getUserByCar(String model, int series) {
       String hql="select p from User p join fetch p.car c where c.model=:model and c.series=:series";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
    }
}
