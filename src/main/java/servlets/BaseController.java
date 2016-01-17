package servlets;

import dao.PhoneBookDAO;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

public abstract class BaseController extends HttpServlet{
    protected PhoneBookDAO dao;

    @Inject
    public void setDao(PhoneBookDAO dao) {
        this.dao = dao;
    }
}
