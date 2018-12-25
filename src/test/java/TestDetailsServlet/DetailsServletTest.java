package TestDetailsServlet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ITKN_16_8_Yarychkina_usermanagement.User;
import ITKN_16_8_Yarychkina_usermanagement.web.MockServletTestCase;

public class DetailsServletTest extends MockServletTestCase {

	 @Override
	    public void setUp() throws Exception {
	        super.setUp();
	        createServlet(DetailsServlet.class);
	    }

	    @Test
	    public void testDetails() {
	        addRequestParameter("cancelButton", "cancel");
	        User user = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
	        assertNull("User is in session", user);
	    }

}
