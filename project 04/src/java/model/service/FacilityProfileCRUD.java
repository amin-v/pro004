package model.service;

import commons.exceptions.BusinessException;
import configController.HibernateUtil;
import controller.facilityController.FacilityProfileBiz;
import model.entity.FacilityProfile;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static commons.ExceptionMessages.*;

public class FacilityProfileCRUD {

    private static Logger logger = Logger.getLogger(FacilityProfileBiz.class);

    public static void persistFacilityProfile(FacilityProfile facilityProfile) throws HibernateException, BusinessException {
        Session session = null;
        try {
            logger.debug("start creating facilityProfile");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(facilityProfile);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Loan Type dose not created.");
            throw new BusinessException(Facility_Profile_NotFound);
        } finally {
            session.close();
            logger.debug("End of Creating Loan Type.");
        }
    }

    public static List<FacilityProfile> findAll() throws HibernateException, BusinessException {
        Session session = null;
        List<FacilityProfile> facilityProfileList;
        try {
            logger.debug("start finding all loanType");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            facilityProfileList = session.createQuery("select person from facilityProfile person").getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Loan Type dose not created.");
            throw new BusinessException(Facility_Profile_NotFound);
        } finally {
            session.close();
            logger.debug("End of finding loan type find.");
        }
        return facilityProfileList;
    }

    public static List<FacilityProfile> findById(long loanTypeId) throws HibernateException, BusinessException {
        Session session = null;
        List<FacilityProfile> facilityProfileList;
        try {
            logger.debug("start finding facilityProfile id ");
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            facilityProfileList = session.createQuery("select  person from facilityProfile person where person.Id=:LoanType_ID ").setParameter("LoanType_ID", loanTypeId).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("facilityProfile dose not created.");
            throw new BusinessException(FacilityProfile_entity_exist);
        } finally {
            session.close();
            logger.info("End of finding facilityProfile.");
        }
        return facilityProfileList;
    }

}