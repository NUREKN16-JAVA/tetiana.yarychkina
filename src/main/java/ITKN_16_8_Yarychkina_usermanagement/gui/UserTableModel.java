package ITKN_16_8_Yarychkina_usermanagement.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ITKN_16_8_Yarychkina_usermanagement.User;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {"ID", "Имя", "Фамилия"};
	private static final Class<?>[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	private static final String[] COLUMN_NAME = null;
	private List users = null;
	
	public UserTableModel(Collection users){
		this.users = new ArrayList(users);
	}
	
	@Override
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return users.size();
	}
	
	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return COLUMN_NAME[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

}
