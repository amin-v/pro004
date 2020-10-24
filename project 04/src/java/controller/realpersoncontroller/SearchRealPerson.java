package controller.realpersoncontroller;

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

@WebServlet("/realPersonController/SearchRealPerson")
public class SearchRealPerson extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String family = req.getParameter("family");
        String fatherName = req.getParameter("fatherName");
        String nationalCode = req.getParameter("nationalCode");
        String CustomerNumber = req.getParameter("customerNumber");
        List<RealPerson> realPersonList = null;
        try {
            if (nationalCode == null & CustomerNumber == null) {
                realPersonList = RealPersonCRUD.retrieveRealCustomer(name, family, fatherName);
            } else if (name == null & family == null & fatherName == null & CustomerNumber == null) {
                realPersonList = RealPersonCRUD.findByNationalCode(nationalCode);
            } else if (nationalCode == null) {
                realPersonList = RealPersonCRUD.findById(Long.valueOf(CustomerNumber));
            }
            req.setAttribute("realPersons", realPersonList);
            req.getRequestDispatcher("/realPerson/index.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", ExceptionWrapper.getMessage(e));
            resp.sendError(700);
        }
    }
}
