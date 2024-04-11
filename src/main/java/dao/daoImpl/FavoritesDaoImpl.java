package dao.daoImpl;

import dao.FavoritesDao;
import entity.Car;
import entity.Favorites;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sessionFactory.SessionFactoryImpl;

import java.util.List;

public class FavoritesDaoImpl implements FavoritesDao {

    public FavoritesDaoImpl() {
    }

    @Override
    public boolean addFavorites(Favorites favorites) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.persist(favorites);
            tx.commit();
            session.close();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteFavorites(int id) {
        boolean isDeleted = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Favorites favorites = session.find(Favorites.class, id);
            if (favorites != null) {
                session.remove(favorites);
                isDeleted = true;
            } else {
                isDeleted = false;
            }
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public List<Favorites> showFavorites() {
        List<Favorites> favoritesList = (List<Favorites>) SessionFactoryImpl.getSessionFactory().openSession()
                .createQuery("From Favorites").list();
        return favoritesList;
    }

    @Override
    public List<Favorites> findFavoritesByUser(User user) {
        List<Favorites> favoritesList = (List<Favorites>) SessionFactoryImpl.getSessionFactory().openSession()
                .createQuery("From Favorites f where f.user = :user").setParameter("user", user).list();
        return favoritesList;
    }

    @Override
    public List<Favorites> findFavoritesByCar(Car car) {
        List<Favorites> favoritesList = (List<Favorites>) SessionFactoryImpl.getSessionFactory().openSession()
                .createQuery("From Favorites f where f.car = :car").setParameter("car", car).list();
        return favoritesList;
    }

    @Override
    public Favorites findFavoritesByIdAndUser(int id, User user) {
        Favorites favorites = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            favorites = session.createQuery("FROM Favorites f WHERE f.car.carId = :id AND f.user = :user", Favorites.class)
                    .setParameter("id", id)
                    .setParameter("user", user)
                    .uniqueResult();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return favorites;
    }
}