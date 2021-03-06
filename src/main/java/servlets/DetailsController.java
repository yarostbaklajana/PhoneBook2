package servlets;

import exceptions.ContactNotFoundException;
import exceptions.DAOException;
import models.ContactDetails;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailsController extends BaseController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            int contactId = Integer.parseInt(id);
            ContactDetails contactDetails = dao.getContactDetails(contactId);
            request.setAttribute("contactDetails", contactDetails);
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
