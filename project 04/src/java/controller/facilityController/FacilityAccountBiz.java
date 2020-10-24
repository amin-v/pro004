package controller.facilityController;

import commons.ExceptionWrapper;
import commons.exceptions.BusinessException;
import controller.checkValidation.FacilityAccountValidation;
import model.entity.FacilityAccount;
import model.entity.FacilityProfile;
import model.entity.RealPerson;
import model.service.FacilityAccountCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/facilityAccount/CreatFacilityAccount.do")
public class FacilityAccountBiz extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long customerId = Long.parseLong((req.getParameter("customerId")));
        long amount = Long.parseLong(req.getParameter("amount"));
        double duration = Double.parseDouble(req.getParameter("duration"));
        long Id = Long.parseLong(req.getParameter("id"));
        try {
            FacilityAccount facilityAccount = new FacilityAccount(amount, duration);

            //TODO: replce with load method
            FacilityProfile facilityProfile = new FacilityProfile().setId(Id);
            RealPerson realPerson = new RealPerson().setId(customerId);
            System.out.println(realPerson.getName() +"gfgf");
            facilityAccount.setRealPerson(realPerson);
            facilityAccount.setFacilityProfile(facilityProfile);
            FacilityAccountCRUD.persistFacilityAccount(facilityAccount);
            FacilityAccountValidation.validate(facilityAccount.getAmount(), facilityAccount.getDuration(), facilityAccount.getFacilityProfile().getId());
            req.setAttribute("loanTypes", facilityProfile);
            req.setAttribute("id", Id);

            req.getRequestDispatcher("/loan/result-page.jsp").forward(req, resp);
        } catch (NumberFormatException | BusinessException e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }

    }
}
