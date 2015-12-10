package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;
import models.PhoneNumber;
import models.PhoneType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPhoneNumberController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        int phoneNumber = Integer.parseInt(request.getParameter("number"));
        String phoneType = request.getParameter("phoneType");
        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            dao.addPhoneNumber(new PhoneNumber(phoneNumber, phoneType), contactId);
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
