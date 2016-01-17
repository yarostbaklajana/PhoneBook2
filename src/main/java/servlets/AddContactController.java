package servlets;

import exceptions.DAOException;
import models.Contact;
import validation.ContactValidator;
import validation.ContactValidatorImpl;
import validation.ValidationResult;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddContactController extends BaseController {
    private ContactValidator contactValidator;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        renderAddPage(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Contact contact = new Contact(firstName, lastName);
        ValidationResult result = contactValidator.validate(contact);
        if (!result.getIsValid()) {
            request.setAttribute("errorMessages", result.getErrors());
            request.setAttribute("contact", contact);
            renderAddPage(request, response);
            return;
        }

        try {
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

    @Inject
    public void setContactValidator(ContactValidator contactValidator) {
        this.contactValidator = contactValidator;
    }
}
