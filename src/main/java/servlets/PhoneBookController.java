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
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/PhoneBook")
public class PhoneBookController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorMessages = new ArrayList<String>();

        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            ArrayList<Contact> contacts = dao.getAllContacts();
            request.setAttribute("contacts", contacts);

        } catch (DAOException e) {
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
        }

        request.getRequestDispatcher("WEB-INF/JSPs/mainpage.jsp").forward(request, response);
    }
}
