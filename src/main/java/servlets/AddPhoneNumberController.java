package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;
import models.PhoneNumber;
import models.PhoneType;
import validation.PhoneNumberValidator;
import validation.ValidationResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPhoneNumberController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        String phoneNumber = request.getParameter("number");
        String phoneType = request.getParameter("phoneType");

        try {
            PhoneNumber number = new PhoneNumber(phoneNumber, phoneType);
            PhoneNumberValidator validator = new PhoneNumberValidator();
            ValidationResult result = validator.validate(number);
            PhoneBookDAO dao = new PhoneBookDAO();
            if (!result.getIsValid()) {
                request.setAttribute("phoneNumber", number);
                request.setAttribute("contactId", contactId);
                request.setAttribute("errorMessages", result.getErrors());
                List<PhoneType> types = dao.getListOfPhoneTypes();
                request.setAttribute("types", types);
                renderAddPhonePage(request, response);
                return;
            }
            dao.addPhoneNumber(number, contactId);
            response.sendRedirect("/details?id=" + contactId);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderAddPhonePage(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contact = request.getParameter("contact");
        int contactId = Integer.parseInt(contact);
        request.setAttribute("contactId", contactId);
        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            List<PhoneType> types = dao.getListOfPhoneTypes();
            request.setAttribute("types", types);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
        }

        renderAddPhonePage(request, response);
    }

    private void renderAddPhonePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/addphone.jsp").forward(request, response);
    }
}
