package model.service;

import commons.exceptions.BusinessException;
import configController.HibernateUtil;
import controller.facilityController.FacilityAccountBiz;
import model.entity.FacilityAccount;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static commons.ExceptionMessages.customerNumer_RealPerson;

public class FacilityAccountCRUD {
    //TODO: is class name is facilityAccount or facilityAccountBiz
    private static Logger logger = Logger.getLogger(FacilityAccountBiz.class);

    //TODO: rename persist to persistFacilityAccount
    public static void persistFacilityAccount(FacilityAccount facilityAccount) throws HibernateException, BusinessException {
        //TODO logger.debug("Start method name");
        logger.debug("start creating facilityProfile");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(facilityAccount);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error("facilityAccount dose not created.");
            throw new BusinessException(customerNumer_RealPerson);
        } finally {
            session.close();
            logger.debug("End  of creating facilityAccount.");
        }
    }

    public static List<FacilityAccount> findById(long realPersonId) throws BusinessException {
        logger.debug("Start finding ById");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<FacilityAccount> facilityAccountList;
        try {
            facilityAccountList = session.createQuery("select facilityAccount from FacilityAccount  facilityAccount LEFT join fetch facilityAccount.realPerson facility where facilityAccount.realPerson.Id=: realPersonId").setParameter("realPersonId", realPersonId).getResultList();
            transaction.commit();
            return facilityAccountList;
        } catch (HibernateException e) {
            logger.error("FacilityAccount dose not find.");
            e.printStackTrace();
            throw new BusinessException(customerNumer_RealPerson);
        } finally {
            session.close();
            logger.debug("End of findingById");
        }
    }
}