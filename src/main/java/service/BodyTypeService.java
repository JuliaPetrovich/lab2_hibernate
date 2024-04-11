package service;

import entity.BodyType;
import java.util.List;

public interface BodyTypeService {
    boolean addBodyType(BodyType bodyType);
    boolean updateBodyType(BodyType bodyType);
    boolean deleteBodyType(int id);
    List<BodyType> showBodyTypes();
    BodyType findBodyTypeById(int id);
    BodyType findBodyTypeByName(String name);
}