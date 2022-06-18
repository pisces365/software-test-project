package dao;

import entity.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class UserDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	// �����û�
	public boolean addUser(User user) {
		if(!verifyUsername(user.getUsername())) {
			hibernateTemplate.save(user);
			return true;
		}
		return false;
	}
	
	// ��֤�û����Ƿ����
	public boolean verifyUsername(String username) {
		List<User> users = (List<User>) hibernateTemplate.find("from User where username=?", username);
		return users.isEmpty() ? false:true;
	}
	
	// ��֤�����Ƿ���ȷ
	public boolean verifyPassword(String username, String password) {
		List queryList = hibernateTemplate.find("select password from User where username=?", username);
		
		return ( queryList.get(0).toString() ).equals(password);
	}
}