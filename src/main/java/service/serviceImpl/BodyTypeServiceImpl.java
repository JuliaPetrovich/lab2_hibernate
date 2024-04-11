package service.serviceImpl;

import dao.BodyTypeDao;
import dao.daoImpl.BodyTypeDaoImpl;
import entity.BodyType;
import org.hibernate.HibernateException;
import service.BodyTypeService;

import java.util.List;

public class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeDao bodyTypeDao = new BodyTypeDaoImpl();

    public BodyTypeServiceImpl() {
    }

    @Override
    public boolean addBodyType(BodyType bodyType) {
        boolean isAdded = false;
        try {
            bodyTypeDao.addBodyType(bodyType);
            isAdded = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean updateBodyType(BodyType bodyType) {
        boolean isUpdated = false;
        try {
            bodyTypeDao.updateBodyType(bodyType);
            isUpdated = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteBodyType(int id) {
        boolean isDeleted = false;
        try {
            isDeleted = bodyTypeDao.deleteBodyType(id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public List<BodyType> showBodyTypes() {
        List<BodyType> bodyTypes = null;
        try {
            bodyTypes = bodyTypeDao.showBodyTypes();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return bodyTypes;
    }

    @Override
    public BodyType findBodyTypeById(int id) {
        BodyType bodyType = null;
        try {
            bodyType = bodyTypeDao.findBodyTypeById(id);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return bodyType;
    }

    @Override
    public BodyType findBodyTypeByName(String name) {
        BodyType bodyType = null;
        try {
            bodyType = bodyTypeDao.findBodyTypeByName(name);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return bodyType;
    }
}