package ITKN_16_8_Yarychkina_usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ITKN_16_8_Yarychkina_usermanagement.User;
import ITKN_16_8_Yarychkina_usermanagement.db.DaoFactory;
import ITKN_16_8_Yarychkina_usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {
	private static final String ADD_JSP = "/add,jsp";

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(ADD_JSP).forward(req, resp);
    }

    @Override
    protected void processUser(User user) throws ReflectiveOperationException, DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}
