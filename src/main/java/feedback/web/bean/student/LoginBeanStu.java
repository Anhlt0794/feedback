package feedback.web.bean.student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Student;
import feedback.core.services.LoginServices;
import feedback.core.services.StudentServer;

@ManagedBean
@SessionScoped
public class LoginBeanStu {
	@ManagedProperty("#{loginServices}")
	private LoginServices loginUser;
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;

	private String username;
	private String password;
	private Student student = new Student();
	private boolean studentIslogin = false;
	@PostConstruct
	public void init(){
	}
	public StudentServer getStudentServer() {
		return studentServer;
	}
	public void setStudentServer(StudentServer studentServer) {
		this.studentServer = studentServer;
	}
	public void loginStudent() {
		try {
			student.setStudentNo(username);
			student.setStudentPass(password);
			Student stu = loginUser.loginStudent(student);
			if (stu.getStudentID()!=0) {
				setStudentIslogin(true);
				setStudent(stu);
			} else
				setStudentIslogin(false);
		} catch (Exception e) {

		}
		System.out.println(username + "   "+ password);

	}

	public void logout() {
		setStudent(new Student());
		setStudentIslogin(false);
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isStudentIslogin() {
		return studentIslogin;
	}

	public void setStudentIslogin(boolean studentIslogin) {
		this.studentIslogin = studentIslogin;
	}

}
