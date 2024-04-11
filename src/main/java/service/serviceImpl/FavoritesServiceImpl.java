package service.serviceImpl;

import dao.FavoritesDao;
import dao.daoImpl.FavoritesDaoImpl;
import entity.Car;
import entity.Favorites;
import entity.User;
import org.hibernate.HibernateError;
import service.FavoritesService;

import java.util.List;

public class FavoritesServiceImpl implements FavoritesService {

    FavoritesDao favoritesDao = new FavoritesDaoImpl();

    public FavoritesServiceImpl() {}

    @Override
    public boolean addFavorites(Favorites favorites) {
        boolean isAdded = false;
        try {
            favoritesDao.addFavorites(favorites);
            isAdded = true;
        } catch (HibernateError e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean deleteFavorites(int id) {
        boolean isDeleted = false;
        try {
            isDeleted = favoritesDao.deleteFavorites(id);
        } catch (HibernateError e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public List<Favorites> showFavorites() {
        List<Favorites> favoritesList = null;
        try {
            favoritesList = favoritesDao.showFavorites();
        } catch (HibernateError e) {
            e.printStackTrace();
        }
        return favoritesList;
    }

    @Override
    public List<Favorites> findFavoritesByUser(User user) {
        List<Favorites> favoritesList = null;
        try {
            favoritesList = favoritesDao.findFavoritesByUser(user);
        } catch (HibernateError e) {
            e.printStackTrace();
        }
        return favoritesList;
    }

    @Override
    public List<Favorites> findFavoritesByCar(Car car) {
        List<Favorites> favoritesList = null;
        try {
            favoritesList = favoritesDao.findFavoritesByCar(car);
        } catch (HibernateError e) {
            e.printStackTrace();
        }
        return favoritesList;
    }

    public Favorites findFavoritesByIdAndUser(int id, User user) {
        return favoritesDao.findFavoritesByIdAndUser(id, user);
    }
}