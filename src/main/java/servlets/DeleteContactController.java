package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yaros on 19.11.2015.
 */
@WebServlet("/PhoneBook/delete")
public class DeleteContactController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        ArrayList<String> errorMessages = new ArrayList<String>();
        if (id != 0) {

            try {
                PhoneBookDAO dao = new PhoneBookDAO();
                dao.deleteContact(id);
            } catch (DAOException e) {
            }
        }
        response.sendRedirect("/phonebook");

    }
}
