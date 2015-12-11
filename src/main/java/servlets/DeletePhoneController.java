package servlets;

import dao.PhoneBookDAO;
import exceptions.DAOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletePhoneController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneIdString = request.getParameter("phoneId");
        int id = Integer.parseInt(phoneIdString);
        int contactId = Integer.parseInt(request.getParameter("contactId"));
        try {
            PhoneBookDAO dao = new PhoneBookDAO();
            dao.deletePhoneNumber(id);
            response.sendRedirect("/details?id=" + contactId);
        } catch (DAOException e) {
            List<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
            request.setAttribute("errorMessages", errorMessages);
            request.getRequestDispatcher("WEB-INF/JSPs/details.jsp").forward(request, response);
        }
    }
}
