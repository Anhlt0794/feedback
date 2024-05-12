package feedback.web.bean.teacher;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Teacher;
import feedback.core.services.LoginServices;

@ManagedBean
@SessionScoped
public class LoginTeacherBean {
	@ManagedProperty("#{loginServices}")
	private LoginServices loginUser;

	private String username;
	private String password;
	private Teacher teacher = new Teacher();
	private boolean teacherIsLogin = false;

	public void loginTeacher() {
		try {
			teacher.setTeacherNo(username);
			teacher.setTeacherPass(password);
			Teacher tc = loginUser.loginTeacher(teacher);
			if (tc.getTeacherID() != 0) {
				setTeacherIsLogin(true);
				setTeacher(tc);
			} else
				setTeacherIsLogin(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void logoutTeacher() {
		setTeacher(new Teacher());
		setTeacherIsLogin(false);
	}

	public LoginServices getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginServices loginUser) {
		this.loginUser = loginUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public boolean isTeacherIsLogin() {
		return teacherIsLogin;
	}

	public void setTeacherIsLogin(boolean teacherIsLogin) {
		this.teacherIsLogin = teacherIsLogin;
	}

}
