package model.service;

import commons.exceptions.BusinessException;
import configController.HibernateUtil;
import model.entity.RealPerson;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static commons.ExceptionMessages.REPETETIVE_NATIONAL_CODE;
import static commons.ExceptionMessages.RealCustomer_Entity_NOT_FOUND;

public class RealPersonCRUD {

    private static Logger logger = Logger.getLogger(RealPerson.class);

    public static void persistRealPerson(RealPerson person) throws Exception {
        logger.debug("start creating RealCustomer");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(person);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error("real customer dose not created.");
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        } finally {
            session.close();
            logger.debug("End of creating real customer.");
        }
    }

    public static void update(RealPerson realPerson) throws Exception {
        Session session = null;
        try {
            logger.debug("start updating realCustomer");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            RealPerson hibernatePerson = session.find(RealPerson.class, realPerson.getId());
            hibernatePerson.setName(realPerson.getName());
            hibernatePerson.setBirthDate(realPerson.getBirthDate());
            hibernatePerson.setFamily(realPerson.getFamily());
            hibernatePerson.setFatherName(realPerson.getFatherName());
            hibernatePerson.setNationalCode(realPerson.getNationalCode());
            session.persist(hibernatePerson);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not updated.");
            e.printStackTrace();
            throw new BusinessException(REPETETIVE_NATIONAL_CODE);
        } finally {
            session.close();
            logger.debug("End of updating real customer.");
        }
    }


    public static void delete(RealPerson realPerson) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            RealPerson hibernatePerson = session.find(RealPerson.class, realPerson.getId());
            session.remove(hibernatePerson);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not deleted.");
            e.printStackTrace();
        } finally {
            session.close();
            logger.debug("End of deleting RealCustomer");
        }
    }

    public static List<RealPerson> findAll() throws Exception {
        Session session = null;
        List<RealPerson> realPersonList;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            realPersonList = session.createQuery("select  person  from realPerson person").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
            logger.debug("End of finding realCustomer");
        }
        return realPersonList;
    }


    public static List<RealPerson> findById(Long id) throws Exception {
        Session session = null;
        List<RealPerson> realPersonList;
        try {
            logger.debug("start finding realCustomer by Id ");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            realPersonList = session.createQuery("select  person from realPerson person where  person.id=:id").setParameter("id", id).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
            logger.info("End of retrieving real customer .");
        }
        return realPersonList;
    }


    public static List<RealPerson> findByNationalCode(String nationalCode) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RealPerson> realPersonList;
        try {
            logger.debug("start findingNationalCode");
            realPersonList = session.createQuery("select  person  from realPerson person where person.nationalCode=:nationalCode").setParameter("nationalCode", nationalCode).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
            logger.debug("End of retrieving real customer nationalCode.");
        }
        return realPersonList;
    }

    public static List<RealPerson> findByName(String name) throws Exception {
        Session session = null;
        List<RealPerson> realPersonList;
        try {
            logger.debug("start finding realCustomer name");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            realPersonList = session.createQuery("select  person  from realPerson person where person.name=:name").setParameter("name", name).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer name dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
            logger.debug("End of finding realCustomer name");
        }
        return realPersonList;
    }

    public static List<RealPerson> findByFamily(String family) throws Exception {
        Session session = null;
        List<RealPerson> realPersonList = null;
        try {
            logger.debug("start retrieving realCustomer family");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            realPersonList = session.createQuery("select  person  from realPerson person where person.family=:family").setParameter("family", family).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("realCustomer family dose not retrieved");
            e.printStackTrace();
        } finally {
            session.close();
            logger.debug("End of retrieving real customer family.");
        }
        return realPersonList;
    }

    public static List<RealPerson> conditionalFind(String where, Map<String, Object> parameterList) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RealPerson> realPersonList;
        try {
            logger.debug("start conditionalFind");
            Query query = session.createQuery("select person from realPerson person " + where);
            Set<String> keys = parameterList.keySet();
            for (String key : keys) {
                query.setParameter(key, parameterList.get(key));
            }
            realPersonList = query.getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("real customer dose not retrieved.");
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
        }
        return realPersonList;
    }

    //..................................
    public static List<RealPerson> retrieveRealCustomer(String name, String family, String fatherName) throws BusinessException {
        Session session = null;
        try {
            logger.debug("start real customer conditionalFind retrieved.");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            List<RealPerson> realCustomers = null;
            Query query = null;

            if (!name.equals("") && !family.equals("") && !fatherName.equals("")) {
                query = session.createQuery("from realPerson realCustomer where name = :name and family = :family and fatherName = :fatherName");
                query.setParameter("name", name);
                query.setParameter("family", family);
                query.setParameter("fatherName", fatherName);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!name.equals("") && !family.equals("")) {
                query = session.createQuery("from realPerson realCustomer where name = :name and family = :family");
                query.setParameter("name", name);
                query.setParameter("family", family);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!name.equals("") && !fatherName.equals("")) {
                query = session.createQuery("from realPerson realCustomer where name = :firstName and fatherName = :fatherName");
                query.setParameter("firstName", name);
                query.setParameter("fatherName", fatherName);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!family.equals("") && !fatherName.equals("")) {
                query = session.createQuery("from realPerson realCustomer where family = :lastName and fatherName = :fatherName");
                query.setParameter("lastName", family);
                query.setParameter("fatherName", fatherName);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!fatherName.equals("")) {
                query = session.createQuery("from realPerson realCustomer where fatherName = :fatherName");
                query.setParameter("fatherName", fatherName);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (name.equals("") && family.equals("") && fatherName.equals("")) {
                query = session.createQuery("from realPerson realCustomer");
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!name.equals("")) {
                query = session.createQuery("from realPerson realCustomer where name = :name ");
                query.setParameter("name", name);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            } else if (!family.equals("")) {
                query = session.createQuery("from realPerson realCustomer where family = :family ");
                query.setParameter("family", family);
                realCustomers = query.getResultList();
                transaction.commit();
                return realCustomers;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new BusinessException(RealCustomer_Entity_NOT_FOUND);
        } finally {
            session.close();
            logger.debug("End of real customer conditionalFind retrieved.");
        }
        return null;
    }
}
