package ITKN_16_8_Yarychkina_usermanagement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private User user;
	private Date dateOfBirth;

	@BeforeEach
	void setUp() throws Exception {
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.MAY, 12);
		dateOfBirth = calendar.getTime();
	}

	
	@Test
    public void testGetFullname() {
		user.setFirstName("Tetiana");
		user.setLastName("Yarychkina");
        assertEquals("Tetiana, Yarychkina", user.getFullName());
    }

    @Test
    public void testGetAge() {
        user.setDateOfBirth(dateOfBirth);
        assertEquals(2018-1999, user.getAge());
    }
}
