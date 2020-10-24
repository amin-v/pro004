package model.service;

import commons.exceptions.BusinessException;
import configController.HibernateUtil;
import controller.facilityController.GrantConditionBiz;
import model.entity.GrantCondition;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static commons.ExceptionMessages.GrandCondition_NotFound;
import static commons.ExceptionMessages.THE_CONDITIONS_IS_NOT_TRUE;

public class GrantConditionalCRUD {

    private static Logger logger = Logger.getLogger(GrantConditionBiz.class);

    public static void persistGrantConditional(GrantCondition grantCondition) throws BusinessException {
        Session session = null;
        try {
            logger.debug("start creating GrantCondition");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
//            FacilityProfileCRUD.getInstance().persist(facilityProfile);
//            for (GrantCondition grantCondition: grantConditions){
//                grantCondition.getFacilityProfile().setLoanTypeId(facilityProfile.getLoanTypeId());
//            }
            //FacilityProfileCRUD.persist(facilityProfile);
            session.persist(grantCondition);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Grant Conditions dose not created.");
            throw new BusinessException(THE_CONDITIONS_IS_NOT_TRUE);
        } finally {
            session.close();
            logger.debug("End of creating Grant Conditions.");
        }
    }

    public static List<GrantCondition> finByName(String name) throws BusinessException {
        Session session = null;
        List<GrantCondition> grantConditionList;
        try {
            logger.debug("start finding GrantCondition name ");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            grantConditionList = session.createQuery("select grantCondition from GrantCondition grantCondition left join fetch grantCondition.facilityProfile facility where grantCondition.name=:name").setParameter("name", name).getResultList();
            transaction.commit();

        } catch (HibernateException e) {
            logger.error("Grant Conditions name dose not find.");
            e.printStackTrace();
            throw new BusinessException(GrandCondition_NotFound);
        } finally {
            session.close();
            logger.debug("End of  finding Grant Conditions Name.");
        }
        return grantConditionList;
    }

    public static List<GrantCondition> findByLoanId(long Id) throws BusinessException {
        List<GrantCondition> grantConditionList;
        logger.debug("starting finding GrantConditions Id ");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            grantConditionList = session.createQuery("select grantCondition from GrantCondition grantCondition left join fetch grantCondition.facilityProfile facility where grantCondition.facilityProfile.Id=:Id").setParameter("Id", Id).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Grant Conditions id dose not find.");
            e.printStackTrace();
            throw new BusinessException(GrandCondition_NotFound);
        } finally {
            session.close();
            logger.debug("End of finding Grant Conditions id.");
        }
        return grantConditionList;
    }

    public static List<GrantCondition> findAll() throws Exception {
        Session session = null;
        List<GrantCondition> grantConditionList;
        try {
            logger.debug("starting finding all GrantCondition ");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            grantConditionList = session.createQuery("select  grantCondition  from GrantCondition grantCondition").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Grant Conditions dose not find.");
            e.printStackTrace();
            throw new BusinessException(GrandCondition_NotFound);
        } finally {
            session.close();
            logger.info("End of finding all Grant Conditions.");
        }
        return grantConditionList;
    }
}


