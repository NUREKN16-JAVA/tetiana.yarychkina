package ITKN_16_8_Yarychkina_usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;

import ITKN_16_8_Yarychkina_usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase{
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	private static final Long ID = 0L;
	private User user;
	
	@Override
	public void setUp() throws Exception {
		//super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() throws DatabaseException{
		assertNull(user.getId());
        User userResult = dao.create(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(user.getFirstName(),userResult.getFirstName());
        assertEquals(user.getLastName(),userResult.getLastName());
        assertEquals(user.getDateOfBirth(),userResult.getDateOfBirth());
		
	}
	
	@Test
	public void testFindAll(){
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", 
				"jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
				  .getResourceAsStream("userDataSet.xml"));
		return dataSet;
	}

	 @Test
	    public void testFind () throws DatabaseException {
	        User testUser = dao.find(ID);
	        assertNotNull(testUser);
	        assertEquals( user.getFirstName(),testUser.getFirstName());
	        assertEquals(user.getLastName(),testUser.getLastName());
	    }

	    
	    @Test
	    public void testUpdate() throws DatabaseException {
	        User user = new User("Ivan","Komarov",new Date());
	        user.setId(0L);
	        dao.update(user);
	        User testUser = dao.find(user.getId());
	        assertNotNull(testUser);
	        assertEquals(user.getLastName(), testUser.getLastName());
	    }

	    
	    @Test
	    public void testDelete() throws DatabaseException {
	        User testUser = new User(ID, "Ivan", "Ivanov", new Date());
	        dao.delete(testUser);
	        assertNull(dao.find(ID));
	    }
}
