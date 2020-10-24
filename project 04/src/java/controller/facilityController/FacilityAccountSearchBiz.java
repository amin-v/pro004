package controller.facilityController;

import commons.ExceptionWrapper;
import model.entity.RealPerson;
import model.service.RealPersonCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/facilityAccount/findByCustomerNumber.do")
public class FacilityAccountSearchBiz extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long customerNumber = Long.parseLong(req.getParameter("customerNumber"));
            List<RealPerson> realPersonList= RealPersonCRUD.findById(customerNumber);
            req.setAttribute("realPersons",realPersonList);
            req.getRequestDispatcher("/loan/FacilityAccount.jsp").forward(req,resp);
        }catch (Exception e){
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}
