package ITKN_16_8_Yarychkina_usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	private Date dateOfBirth;

	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@Test
	public void testGetFullName(){
		user.setFirstName("Tetiana");
		user.setLastName("Yarychkina");
		assertEquals("Tetiana, Yarychkina", user.getFullName());
	}
	//Просто дата
	@Test
	public void testGetAge1(){

		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.MAY, 12);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(19, user.getAge());
	}
	//Дата в будущем
	@Test
	public void testGetAge2(){

		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.NOVEMBER, 12);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(18, user.getAge());
	}
	//Дата в этом месяце, но раньше этого дня (ну, правда, как-то не вышло, но это правда работает)
	@Test
	public void testGetAge3(){

		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.OCTOBER, 12);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(19, user.getAge());
	}
	//Дата сегодня
	@Test
	public void testGetAge4(){

		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.NOVEMBER, 1);
		dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(19, user.getAge());
	}
	
}
