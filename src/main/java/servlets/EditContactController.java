package servlets;

import exceptions.ContactNotFoundException;
import exceptions.DAOException;
import models.Contact;
import validation.ContactValidator;
import validation.ValidationResult;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditContactController extends BaseController {
    private ContactValidator contactValidator;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Contact contact = new Contact(id, firstName, lastName);
        ValidationResult validationResult = contactValidator.validate(contact);
        if (validationResult.getIsValid() == false) {
            request.setAttribute("errorMessages", validationResult.getErrors());
            request.setAttribute("contact", contact);
            renderEditPage(request, response);
            return;
        }

        try {
            dao.updateContact(contact);
            response.sendRedirect("/details?id=" + id);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderEditPage(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);
            renderEditPage(request, response);
        } catch (ContactNotFoundException e) {
            renderErrorPage(request, response);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderEditPage(request, response);
        }
    }

    private void renderErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/contactNotFound.jsp").forward(request, response);
    }

    private void renderEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/edit.jsp").forward(request, response);
    }

    @Inject
    public void setContactValidator(ContactValidator contactValidator) {
        this.contactValidator = contactValidator;
    }
}
