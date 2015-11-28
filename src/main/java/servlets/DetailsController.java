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

@WebServlet("/PhoneBook/details")
public class DetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<String>();

        PhoneBookDAO dao = new PhoneBookDAO();
        String id = request.getParameter("id");

        try {
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);
        } catch (DAOException e) {
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            renderDetailsPage(request, response);
        }
        renderDetailsPage(request, response);
    }

    private void renderDetailsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/JSPs/details.jsp").forward(request, response);
    }


}
