package controller.facilityController;

import commons.ExceptionWrapper;
import commons.exceptions.BusinessException;
import controller.checkValidation.GrantConditionValidation;
import model.entity.FacilityProfile;
import model.entity.GrantCondition;
import model.service.GrantConditionalCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/facilityProfile/GrantConditionBiz.do")
public class GrantConditionBiz extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException {
        try {

            String facilityName = req.getParameter("facilityName");
            String interestRate = req.getParameter("interestRate");
            long rowNumber = Long.parseLong((req.getParameter("rowNumber")));

            GrantCondition grantCondition = new GrantCondition();
            grantCondition.setName(req.getParameter("grantName"));
//                if(req.getParameter("minDuration")!=null)
            grantCondition.setMinDuration(Long.parseLong(req.getParameter("minDuration")));
            grantCondition.setMaxDuration(Long.parseLong(req.getParameter("maxDuration")));
            grantCondition.setMaxAmount(BigDecimal.valueOf(Long.parseLong(req.getParameter("maxAmount"))));
            grantCondition.setMinAmount(BigDecimal.valueOf(Long.parseLong(req.getParameter("minAmount"))));
            GrantConditionValidation.validate(grantCondition);

            FacilityProfile facilityProfile = new FacilityProfile().setFacilityName(facilityName).setInterestRate(interestRate);
            grantCondition.setFacilityProfile(facilityProfile);
            GrantConditionalCRUD.persistGrantConditional(grantCondition);
            req.setAttribute("title", "تایید ثبت تسهیلات");
            req.setAttribute("header", "تسهیلات " + facilityName + " با موفقیت ثبت شد.");
            resp.sendRedirect("/loan/result-page.jsp");
        } catch (NumberFormatException | BusinessException e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}
