package ITKN_16_8_Yarychkina_usermanagement.web;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ITKN_16_8_Yarychkina_usermanagement.db.DaoFactory;
import ITKN_16_8_Yarychkina_usermanagement.db.MockDaoFactory;

public class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }

    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());

        DaoFactory.init(properties);

        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }

    public void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();

    }
}
