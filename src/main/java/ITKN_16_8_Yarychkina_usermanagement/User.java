package ITKN_16_8_Yarychkina_usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

//import ua.nure.kn16.yarychkina.usemanagment.User;

public class User implements Serializable{
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirthd;
	
	public User(){
        super();
    }

    public User(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirthd = dateOfBirthd;
    }

    public User(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirthd = dateOfBirth;
    }

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirthd;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirthd = dateOfBirth;
	}
	public Object getFullName() {
		return getFirstName() + ", " + getLastName();
	}
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDate = calendar.get(Calendar.DATE);

        calendar.setTime(dateOfBirthd);
        final int birthYear = calendar.get(Calendar.YEAR);
        final int birthMonth = calendar.get(Calendar.MONTH);
        final int birthDate = calendar.get(Calendar.DATE);

        int age = currentYear - birthYear;
        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDate < birthDate)) {
            --age;
        }
        return age;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirthd == null) ? 0 : dateOfBirthd.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirthd == null) {
			if (other.dateOfBirthd != null)
				return false;
		} else if (!dateOfBirthd.equals(other.dateOfBirthd))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
}
