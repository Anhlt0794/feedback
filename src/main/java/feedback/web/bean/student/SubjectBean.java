package feedback.web.bean.student;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import feedback.core.models.entities.Subject;
import feedback.core.models.entities.Teacher;
import feedback.core.services.ViewSubjectServer;

@ManagedBean
@ViewScoped
public class SubjectBean {
	private String HK;
	private List<SelectItem> HKs;
	private Subject subject = new Subject();
	private Teacher teacher = new Teacher();
	private List<Subject> listSubject = new ArrayList<Subject>();
	private List<Teacher> listTeacher = new ArrayList<Teacher>();
	private String selectSub;
	private List<SelectItem> listNameSub;
	
	@ManagedProperty("#{viewSubjectServer}")
	private ViewSubjectServer viewSubjectServer;
	@ManagedProperty("#{loginBeanStu}")
	private LoginBeanStu stB;

	@PostConstruct
	public void init() {
		HKs = new ArrayList<SelectItem>();
		HKs.add(new SelectItem("HK1-22"));
		HKs.add(new SelectItem("HK2-22"));
		HKs.add(new SelectItem("HK1-22"));
		HKs.add(new SelectItem("HK2-23"));
		HKs.add(new SelectItem("HK3-23"));
		HKs.add(new SelectItem("HK1-24"));
		HKs.add(new SelectItem("HK2-24"));
		
		listSubject = viewSubjectServer.loadAllSubject();
		
		listNameSub = new ArrayList<SelectItem>();
		listNameSub.add(new SelectItem());

	}

	public String getHK() {
		return HK;
	}

	public void setHK(String hK) {
		HK = hK;
	}

	public List<SelectItem> getHKs() {
		return HKs;
	}

	public void setHKs(List<SelectItem> hKs) {
		HKs = hKs;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Subject> getListSubject() {
		return listSubject;
	}

	public void setListSubject(List<Subject> listSubject) {
		this.listSubject = listSubject;
	}

	public List<Teacher> getListTeacher() {
		return listTeacher;
	}

	public void setListTeacher(List<Teacher> listTeacher) {
		this.listTeacher = listTeacher;
	}

	public ViewSubjectServer getViewSubjectServer() {
		return viewSubjectServer;
	}

	public void setViewSubjectServer(ViewSubjectServer viewSubjectServer) {
		this.viewSubjectServer = viewSubjectServer;
	}

	public LoginBeanStu getStB() {
		return stB;
	}

	public void setStB(LoginBeanStu stB) {
		this.stB = stB;
	}

	public String getSelectSub() {
		return selectSub;
	}

	public void setSelectSub(String selectSub) {
		this.selectSub = selectSub;
	}

	public List<SelectItem> getListNameSub() {
		return listNameSub;
	}

	public void setListNameSub(List<SelectItem> listNameSub) {
		this.listNameSub = listNameSub;
	}

}
