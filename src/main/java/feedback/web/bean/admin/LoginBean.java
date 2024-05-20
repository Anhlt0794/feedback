package feedback.web.bean.admin;
/**
 * Created by Anh on 12/19/2023.
 */
import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import feedback.core.models.entities.Manager;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Teacher;
import feedback.core.services.LoginServices;
import feedback.core.services.ManagerServices;

@ManagedBean
@SessionScoped
public class LoginBean {
	/**
	 * 
	 */
	@ManagedProperty("#{loginServices}")
	private LoginServices loginUser;
	private String username;
	private String password;
	private Manager manager = new Manager();
	private Teacher teacher = new Teacher();
	private Student student = new Student();
	private boolean mangerIslogin = false;
	private boolean studentIslogin = false;
	private boolean teacherIslogin = false;
	private boolean adminIslogin = false;
	private StreamedContent content;
	@ManagedProperty(value = "#{managerServices}")
	private ManagerServices impl;

	@PostConstruct
	public void init() {
		
	}

	public String loginManager() {
		try {
			manager.setManagerEmail(manager.getManagerNo());
			Manager other = loginUser.loginManager(manager);
			StreamedContent imager;
			if (other.getManagerID()!=0) {
				setManager(other);
				setMangerIslogin(true);
				if (other.getManagerLevel() == 6)
					setAdminIslogin(true);
				if (manager.getAvatar().length > 100) {
					imager = new DefaultStreamedContent(
							new ByteArrayInputStream(manager.getAvatar()),
							"image/png");
					setContent(imager);
				}
			}
		} catch (Exception e) {
		}
		return "dashboard";
	}

	public String login() {
		String view = "";
		try {
			Manager manager = new Manager();
			manager.setManagerNo(username);
			manager.setManagerEmail(username);
			manager.setManagerPass(password);
			Manager m = loginUser.loginManager(manager);
			if (m.getManagerID()!=0) {
				setManager(m);
				setMangerIslogin(true);
				if (m.getManagerLevel() == 6)
					setAdminIslogin(true);
				view = "manager";
			} else {
				Teacher login = new Teacher();
				login.setTeacherNo(username);
				login.setTeacherPass(password);
				login.setTeacherEmail(username);
				Teacher t = loginUser.loginTeacher(teacher);
				if (t.getTeacherID()!=0) {
					setTeacher(t);
					setTeacherIslogin(true);
					view = "teacher";
				} else {

					Student student = new Student();
					student.setStudentNo(username);
					student.setStudentEmail(username);
					student.setStudentPass(password);
					Student st = loginUser.loginStudent(student);
					if (st!=null) {
						setStudent(st);
						setStudentIslogin(true);
						view = "student";
					}
				}
			}

		} catch (Exception e) {
		}
		return view;
	}

	public String cancel() {
		return null;
	}

	public String logout() {
		setManager(new Manager());
		setMangerIslogin(false);
		setAdminIslogin(false);
		setTeacher(new Teacher());
		setTeacherIslogin(false);
		setStudent(new Student());
		setStudentIslogin(false);
		setUsername("");
		setPassword("");
		return "welcome";
	}
	public String logoutM() {
		setManager(new Manager());
		setMangerIslogin(false);
		setAdminIslogin(false);
		setTeacher(new Teacher());
		setTeacherIslogin(false);
		setStudent(new Student());
		setStudentIslogin(false);
		setUsername("");
		setPassword("");
		return "login";
	}
	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isMangerIslogin() {
		return mangerIslogin;
	}

	public void setMangerIslogin(boolean mangerIslogin) {
		this.mangerIslogin = mangerIslogin;
	}

	public boolean isStudentIslogin() {
		return studentIslogin;
	}

	public void setStudentIslogin(boolean studentIslogin) {
		this.studentIslogin = studentIslogin;
	}

	public boolean isTeacherIslogin() {
		return teacherIslogin;
	}

	public void setTeacherIslogin(boolean teacherIslogin) {
		this.teacherIslogin = teacherIslogin;
	}

	public boolean isAdminIslogin() {
		return adminIslogin;
	}

	public void setAdminIslogin(boolean adminIslogin) {
		this.adminIslogin = adminIslogin;
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

	public LoginServices getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginServices loginUser) {
		this.loginUser = loginUser;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public ManagerServices getImpl() {
		return impl;
	}

	public void setImpl(ManagerServices impl) {
		this.impl = impl;
	}

}
