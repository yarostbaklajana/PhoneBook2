package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;
import models.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaros on 19.11.2015.
 */
@WebServlet("/PhoneBook/add")
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

        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);

        List<String> errorMessages = new ArrayList<String>();

        if (firstName.equals("") || firstName == null) {
            errorMessages.add("The first name field is empty!");
        }

        if (lastName.equals("") || lastName == null) {
            errorMessages.add("The last name field is empty!");
        }

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            renderAddPage(request, response);
            return;
        }

        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            dao.addContact(new Contact(firstName, lastName));
        } catch (DAOException e) {
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderAddPage(request, response);
        }

        response.sendRedirect("/phonebook");

    }

    private void renderAddPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/addpage.jsp").forward(request, response);
    }
}
