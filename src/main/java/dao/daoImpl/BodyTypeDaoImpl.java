package dao.daoImpl;

import dao.BodyTypeDao;
import entity.BodyType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sessionFactory.SessionFactoryImpl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class BodyTypeDaoImpl implements BodyTypeDao {

    public BodyTypeDaoImpl() {
    }

    @Override
    public boolean addBodyType(BodyType bodyType) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.persist(bodyType);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateBodyType(BodyType bodyType) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(bodyType);
            tx.commit();
            session.close();
            isUpdated = true;
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteBodyType(int id) {
        boolean isDeleted = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            BodyType bodyType = session.find(BodyType.class, id);
            if (bodyType != null) {
                session.remove(bodyType);
                isDeleted = true;
            } else {
                isDeleted = false;
            }

            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public BodyType findBodyTypeById(int id) {
        BodyType bodyType = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            bodyType = session.find(BodyType.class, id);

            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return bodyType;
    }

    @Override
    public BodyType findBodyTypeByName(String name) {
        BodyType bodyType = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BodyType> cr = cb.createQuery(BodyType.class);
            Root<BodyType> root = cr.from(BodyType.class);
            cr.select(root).where(cb.equal(root.get("bodyTypeName"), name));
            bodyType = session.createQuery(cr).getSingleResult();

            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return bodyType;
    }

    @Override
    public List<BodyType> showBodyTypes() {
        List<BodyType> bodyTypes = (List<BodyType>) SessionFactoryImpl.getSessionFactory().openSession()
                .createQuery("FROM BodyType").list();
        return bodyTypes;
    }
}