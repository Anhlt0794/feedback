package feedback.web.bean.student;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import feedback.core.models.entities.Student;
import feedback.core.services.StudentServer;

@ManagedBean
@ViewScoped
public class InforBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student stu = new Student();
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{loginBeanStu}")
	private LoginBeanStu stB;
	@PostConstruct
	public void init() {
		
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public StudentServer getStudentServer() {
		return studentServer;
	}

	public void setStudentServer(StudentServer studentServer) {
		this.studentServer = studentServer;
	}

	public LoginBeanStu getStB() {
		return stB;
	}

	public void setStB(LoginBeanStu stB) {
		this.stB = stB;
	}
	

}
