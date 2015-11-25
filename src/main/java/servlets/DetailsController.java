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
 * Created by yaros on 24.11.2015.
 */
@WebServlet("/PhoneBook/details")
public class DetailsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PhoneBookDAO dao = new PhoneBookDAO();
        String id = request.getParameter("id");
        try {
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/JSPs/details.jsp").forward(request, response);
    }
}
