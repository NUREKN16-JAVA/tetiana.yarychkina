package ITKN_16_8_Yarychkina_usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ITKN_16_8_Yarychkina_usermanagement.User;

class HsqldbUserDao implements UserDao {

	private static final String FIND_BY_ID_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String SELECT_ALL_QUERY = FIND_BY_ID_QUERY;
	private static final String INSERT_QUERY = "INSERT INTO user(firstname, lastname, dateofbirth ) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
	public static final String FIND_ALL_QUERY = FIND_BY_ID_QUERY;
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	
	public HsqldbUserDao(){
		}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	



	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if (n != 1){
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
		    ResultSet keys = callableStatement.executeQuery();
			if(keys.next()){
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
		    return user;
		} 
		catch (DatabaseException e){
			throw e;
		}
		catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<>();

        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(new Long(resultSet.getLong(1)));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	@Override
	public void update(User user) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_QUERY);
            statement.setString(1,user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3,new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of updated raws: " + number);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
	}

	@Override
	public void delete(User user) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(DELETE_QUERY);
            statement.setLong(1,user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of deleted raws: " + number);
            }


            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user;
        try {
            user = null;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1,id);
            ResultSet oneUserResultSet = preparedStatement.executeQuery();
           if (oneUserResultSet.next()){
                user = new User();
                user.setId(new Long(oneUserResultSet.getLong("ID")));
                user.setFirstName(oneUserResultSet.getString("FIRSTNAME"));
                user.setLastName(oneUserResultSet.getString("LASTNAME"));
                user.setDateOfBirth(oneUserResultSet.getDate("DATEOFBIRTH"));
            }
            connection.close();
            preparedStatement.close();
            oneUserResultSet.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

}
