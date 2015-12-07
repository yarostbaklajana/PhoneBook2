package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;
import models.Contact;
import validation.ContactValidator;
import validation.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddContactController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("firstName", "");
        request.setAttribute("lastName", "");
        renderAddPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Contact contact = new Contact(firstName, lastName);
        ContactValidator validator = new ContactValidator();
        ValidationResult result = validator.validate(contact);

        if (!result.getIsValid()) {
            request.setAttribute("errorMessages", result.getErrors());
            request.setAttribute("contact", contact);
            renderAddPage(request, response);
            return;
        }

        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            dao.addContact(contact);
            response.sendRedirect("/");
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderAddPage(request, response);
        }

    }

    private void renderAddPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/addpage.jsp").forward(request, response);
    }
}
