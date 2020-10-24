package controller.facilityController;

import commons.ExceptionWrapper;
import controller.checkValidation.FacilityProfileValidation;
import model.entity.FacilityProfile;
import model.service.FacilityProfileCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/facilityProfile/signUp.do")
public class FacilityProfileBiz extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String facilityName = req.getParameter("facilityName");
            String interestRate = req.getParameter("interestRate");
            FacilityProfile facilityProfile = new FacilityProfile();

            facilityProfile.setFacilityName(facilityName).setInterestRate(interestRate);
            FacilityProfileValidation.validate(facilityName.trim(), interestRate.trim());
            FacilityProfileCRUD.persistFacilityProfile(facilityProfile);
            req.setAttribute("facilityName", facilityName);
            req.setAttribute("interestRate", interestRate);
            req.setAttribute("facilityProfile", facilityProfile);

            req.getRequestDispatcher("/loan/GrantCondition.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}