package servlets;

import exceptions.DAOException;
import models.ContactInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class PhoneBookController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<ContactInfo> contacts = dao.getAllContacts();
            request.setAttribute("contacts", contacts);
        } catch (DAOException e) {
            ArrayList<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
        }

        request.getRequestDispatcher("WEB-INF/JSPs/mainpage.jsp").forward(request, response);
    }
}