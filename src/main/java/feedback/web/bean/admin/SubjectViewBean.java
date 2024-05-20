package feedback.web.bean.admin;
/**
 * Created by Anh on 12/19/2023.
 */
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Subject;
import feedback.core.models.entities.Teacher;
import feedback.core.services.SubjectServices;
import feedback.web.util.FacesUtil;

@ManagedBean(name="svb")
@SessionScoped
public class SubjectViewBean {
	@ManagedProperty("#{sv}")
	private SubjectServices services;
	private Subject edit = new Subject();
	private Subject editTmp = new Subject();
	private List<Subject> subjects = new ArrayList<Subject>();
	private List<Subject> select = new ArrayList<Subject>();
	private List<Teacher> selectTeaher = new ArrayList<Teacher>();
	@PostConstruct
	public void init() {
		setSubjects(services.getAll());
	}
		public String view(String type) {
				
				if(select.size()>0) 
				{
					if("e".equalsIgnoreCase(type)){
							edit = select.get(0);
							loadEditTmp();
							return "edit"; 
					}else 
					if("v".equalsIgnoreCase(type)){
						edit = select.get(0);
						loadEditTmp();
						return "view-detail";	
					}else if("ct".equalsIgnoreCase(type)){
						edit = select.get(0);
						loadEditTmp();
						return "edit-teachers";	
					}
				}else{
					info("", "Please select one more code in table!");
				}
				
				return null;
			}
		
		public void update(){
			if((edit.getSubjectNo().equals(editTmp.getSubjectNo()))&&(edit.getSubjectName().equals(editTmp.getSubjectName()))){
				warn("", "Data in form not change!");
				return;
			}else{
				if(editTmp.getSubjectNo().trim().equals("")){
					warn("", "Please enter value ID!");
				}else
				if(editTmp.getSubjectName().trim().equals("")){
						warn("", "Please enter value Name!");
				}else{
					
					try {
						Subject s = services.getByNo(editTmp.getSubjectNo());
						if(s!=null){
							if(s.getSubjectID()!=edit.getSubjectID()){
								error("", edit.getSubjectNo()+ " Has been Database! Please change other values!");
								return;
							}
						}
						edit.setSubjectNo(editTmp.getSubjectNo());
						edit.setSubjectName(editTmp.getSubjectName());
						services.update(edit);
						info("", edit.getSubjectNo()+ " updated!");
					} catch (Exception e) {
						error("", edit.getSubjectNo()+ " Has been Database!");
					}	
				}
				
			}
		
	}
	
		public void loadEditTmp(){
			Subject s = new Subject();
			s.setSubjectNo(edit.getSubjectNo());
			s.setSubjectName(edit.getSubjectName());
			s.setTeacher(edit.getTeacher());
			setEditTmp(s);
		}
		public void info(String title, String detail) {
			FacesUtil.getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail));
		}

		public void warn(String title, String detail) {
			FacesUtil.getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, title, detail));
		}

		public void error(String title, String detail) {
			FacesUtil.getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail));
		}

		public void fatal(String title, String detail) {
			FacesUtil.getFacesContext().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, title, detail));
		}
	public synchronized SubjectServices getServices() {
		return services;
	}
	public synchronized void setServices(SubjectServices services) {
		this.services = services;
	}
	public synchronized Subject getEdit() {
		return edit;
	}
	public synchronized void setEdit(Subject edit) {
		this.edit = edit;
	}
	public synchronized Subject getEditTmp() {
		return editTmp;
	}
	public synchronized void setEditTmp(Subject editTmp) {
		this.editTmp = editTmp;
	}
	public synchronized List<Subject> getSubjects() {
		return subjects;
	}
	public synchronized void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public synchronized List<Subject> getSelect() {
		return select;
	}
	public synchronized void setSelect(List<Subject> select) {
		this.select = select;
	}
	public List<Teacher> getSelectTeaher() {
		return selectTeaher;
	}
	public void setSelectTeaher(List<Teacher> selectTeaher) {
		this.selectTeaher = selectTeaher;
	}
	
	
}
