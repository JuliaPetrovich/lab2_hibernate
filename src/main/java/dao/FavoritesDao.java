package dao;

import entity.Car;
import entity.Favorites;
import entity.User;

import java.util.List;

public interface FavoritesDao {
    boolean addFavorites(Favorites favorites);
    boolean deleteFavorites(int id);
    List<Favorites> showFavorites();
    List<Favorites> findFavoritesByUser(User user);
    List<Favorites> findFavoritesByCar(Car car);
    Favorites findFavoritesByIdAndUser(int id, User user);
}