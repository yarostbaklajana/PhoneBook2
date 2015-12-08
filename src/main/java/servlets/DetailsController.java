package servlets;

import dao.PhoneBookDAO;
import exceptions.ContactNotFoundException;
import exceptions.DAOException;
import models.Contact;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            String id = request.getParameter("id");
            Contact contact = dao.getContact(Integer.valueOf(id));
            request.setAttribute("contact", contact);
        } catch (ContactNotFoundException e) {
            request.getRequestDispatcher("WEB-INF/JSPs/contactNotFound.jsp").forward(request,response);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
        }
        request.getRequestDispatcher("WEB-INF/JSPs/details.jsp").forward(request, response);
    }
}
