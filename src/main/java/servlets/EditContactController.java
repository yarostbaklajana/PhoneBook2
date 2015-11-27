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

/**
 * Created by yaros on 25.11.2015.
 */
@WebServlet("/PhoneBook/edit")
public class EditContactController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int id = Integer.parseInt(request.getParameter("id"));
        PhoneBookDAO dao = new PhoneBookDAO();

        try {
            Contact contact = new Contact(id, firstName, lastName);
            dao.updateContact(contact);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/details?id=" + id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        PhoneBookDAO dao = new PhoneBookDAO();
        try {
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);
            renderEditPage(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private void renderEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/edit.jsp").forward(request, response);
    }
}
