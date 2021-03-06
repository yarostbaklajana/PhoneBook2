package servlets;

import exceptions.DAOException;
import javax.servlet.ServletException;;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteContactController extends BaseController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idString = request.getParameter("id");
            Integer id = Integer.valueOf(idString);
            dao.deleteContact(id);
            response.sendRedirect("/");
        } catch (DAOException e) {
            ArrayList<String> errorMessages = new ArrayList<String>();
            errorMessages.add(e.getMessage());
        }
    }
}
