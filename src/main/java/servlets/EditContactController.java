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


@WebServlet("/PhoneBook/edit")
public class EditContactController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int id = Integer.parseInt(request.getParameter("id"));
        PhoneBookDAO dao = new PhoneBookDAO();
        List<String> errorMessages = new ArrayList<String>();
        if(firstName.equals("")) {
            errorMessages.add("First Name is empty. Enter the First Name.");
        }

        if(lastName.equals("")) {
            errorMessages.add("Last Name is empty. Enter the Last Name.");
        }

        if(!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            renderEditPage(request, response);
            return;
        }

        try {
            Contact contact = new Contact(id, firstName, lastName);
            dao.updateContact(contact);

        } catch (DAOException e) {
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderEditPage(request, response);
        }
        response.sendRedirect("/details?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        PhoneBookDAO dao = new PhoneBookDAO();
        List<String> errorMessages = new ArrayList<String>();

        try {
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);

        } catch (DAOException e) {
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderEditPage(request, response);
        }
        renderEditPage(request, response);
    }

    private void renderEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/edit.jsp").forward(request, response);
    }


}
