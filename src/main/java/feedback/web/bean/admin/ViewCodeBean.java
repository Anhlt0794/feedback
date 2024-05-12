package feedback.web.bean.admin;
/**
 * Created by Hien on 5/19/2015.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Code;
import feedback.core.models.entities.Student;
import feedback.core.services.CodeServices;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.StudentServer;
import feedback.web.util.FacesUtil;

@ManagedBean(name="vcb")
@SessionScoped
public class ViewCodeBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{codeServices}")
	private CodeServices codeServices;
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{entityManager}")
	private EntityManagerServices managerServices;
	@ManagedProperty("#{codeAddBean}")
	private CodeAddBean addBean;
	/**
	 * Edit code
	 * */
	private List<Code> codesView = new ArrayList<Code>();
	private List<Code> selectView = new ArrayList<Code>();
	private Code selectEdit = new Code() ;
	private Code selectEditTmp = new Code();
	private Code viewTmp = new Code();
	/**
	 * Edit Student
	 * */
	private Code selectEditAddStudent = new Code();
	private List<Student> selectEditStudent = new ArrayList<Student>();
	private List<Student> listUpload = new ArrayList<Student>();
	private List<Student> selectAddStudent = new ArrayList<Student>();
 	private List<Code> codeAddListStudent = new ArrayList<Code>();
	private Student addStudent = new Student();
	private String visitListCode;
	private String visitInput;
	@PostConstruct
	public void init() {
		setCodesView(codeServices.getAll());
		
	}
	
	public String viewCode(String type) {
		
		if(selectView.size()>0) 
		{
			if("e".equalsIgnoreCase(type)){
					selectEdit = selectView.get(0);
					loadEditTmp();
					return "EditCode"; 
			}else 
			if("v".equalsIgnoreCase(type)){
				selectEdit = selectView.get(0);
				loadEditTmp();
				return "viewCodeDetail";
				
			}
			if("cs".equalsIgnoreCase(type)){
				
				return "editSelect";
			}
		
		}
		info("", "Please select one more code in table!");
		return null;
	}
	public String viewStudent(String type){
		if("a".equals(type)){
			return "addStudent?includeViewParams=true";
		}else{
			if(selectEditStudent.size()>0){
				if("e".equals(type)){
					return "editStudent";
				}else
					if("d".equals(type)){
						for(Student s: selectEditStudent){
							try {
								managerServices.delete(s);
								info("Success", s.getStudentNo()+  " deleted!");
							} catch (Exception e) {
								error("Error", "Can't delete " + s.getStudentNo() + ". Please try agian later!");
							}
						}
					}else{
						
					}
			}
		}
		return null;
	}
	public void deleteStudent(){
		
	}
	public void deleteCode(){
		if(codesView.size()>0){
			if(selectView.size()>0){
				for(Code c: selectView){
					try {
						managerServices.delete(c);
						info("Success", " " + c.getCodeNo() + " deleted!");
					} catch (Exception e) {
						error("Error", "Can't delete" + c.getCodeNo());
					}
				}
				
			}else{
				info("", "Please select one more code in table!");
			}
		}else{
			info("", "Table code is empty!");
		}
	}
	public void loadEditTmp(){
		Code c = new Code();
		c.setCodeNo(selectEdit.getCodeNo());
		c.setCodeName(selectEdit.getCodeName());
		c.setStudent(selectEdit.getStudent());
		setSelectEditTmp(c);
	}
	public void updateCode(){
				if((selectEdit.getCodeNo().equals(selectEditTmp.getCodeNo()))&&(selectEdit.getCodeName().equals(selectEditTmp.getCodeName()))){
					warn("", "Data in form not change!");
					return;
				}else{
					if(selectEditTmp.getCodeNo().trim().equals("")){
						warn("", "Please enter value Code ID!");
					}else
					if(selectEditTmp.getCodeName().trim().equals("")){
							warn("", "Please enter value Code Name!");
					}else{
						
						try {
							Code cc = codeServices.getCodeByNo(selectEditTmp.getCodeNo());
							if(cc!=null){
							if(cc.getCodeID()!=selectEdit.getCodeID()){
								error("", selectEdit.getCodeNo()+ " Has been Database!");
							return;
							}
							}
							selectEdit.setCodeNo(selectEditTmp.getCodeNo());
							selectEdit.setCodeName(selectEditTmp.getCodeName());
							managerServices.update(selectEdit);
							info("", selectEdit.getCodeNo()+ " updated!");
						} catch (Exception e) {
							error("", selectEdit.getCodeNo()+ " Has been Database!");
						}	
					}
					
				}
			
		}
		
		
		
	
	
	
	public void info(String title,String detail) {
        FacesUtil.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title,detail));
    }
     
    public void warn(String title,String detail) {
    	FacesUtil.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title,detail));
    }
     
    public void error(String title,String detail) {
    	FacesUtil.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,title,detail));
    }
     
    public void fatal(String title,String detail) {
    	FacesUtil.getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title,detail));
    }
    public void setVisitListFalse() {
		setVisitListCode("display: none");
	}

	public void setVisitListTrue() {
		setVisitListCode("");
	}

	public void setVisitInputFalse() {
		setVisitInput("display: none");
	}

	public void setVisitInputTrue() {
		setVisitInput("");
	}
	public CodeServices getCodeServices() {
		return codeServices;
	}


	public void setCodeServices(CodeServices codeServices) {
		this.codeServices = codeServices;
	}


	public StudentServer getStudentServer() {
		return studentServer;
	}


	public void setStudentServer(StudentServer studentServer) {
		this.studentServer = studentServer;
	}


	public EntityManagerServices getManagerServices() {
		return managerServices;
	}


	public void setManagerServices(EntityManagerServices managerServices) {
		this.managerServices = managerServices;
	}


	public List<Code> getCodesView() {
		return codesView;
	}


	public void setCodesView(List<Code> codesView) {
		this.codesView = codesView;
	}


	public List<Code> getSelectView() {
		return selectView;
	}


	public void setSelectView(List<Code> selectView) {
		this.selectView = selectView;
	}
	
	
	public Code getViewTmp() {
		return viewTmp;
	}
	public void setViewTmp(Code viewTmp) {
		this.viewTmp = viewTmp;
	}
	
	
	public Code getSelectEdit() {
		return selectEdit;
	}
	public void setSelectEdit(Code selectEdit) {
		this.selectEdit = selectEdit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Student> getSelectEditStudent() {
		return selectEditStudent;
	}
	public void setSelectEditStudent(List<Student> selectEditStudent) {
		this.selectEditStudent = selectEditStudent;
	}
	public Code getSelectEditTmp() {
		return selectEditTmp;
	}
	public void setSelectEditTmp(Code selectEditTmp) {
		this.selectEditTmp = selectEditTmp;
	}
	public CodeAddBean getAddBean() {
		return addBean;
	}
	public void setAddBean(CodeAddBean addBean) {
		this.addBean = addBean;
	}
	public List<Code> getCodeAddListStudent() {
		return codeAddListStudent;
	}
	public void setCodeAddListStudent(List<Code> codeAddListStudent) {
		this.codeAddListStudent = codeAddListStudent;
	}
	public Student getAddStudent() {
		return addStudent;
	}
	public void setAddStudent(Student addStudent) {
		this.addStudent = addStudent;
	}
	public String getVisitListCode() {
		return visitListCode;
	}
	public void setVisitListCode(String visitListCode) {
		this.visitListCode = visitListCode;
	}
	public String getVisitInput() {
		return visitInput;
	}
	public void setVisitInput(String visitInput) {
		this.visitInput = visitInput;
	}
	public Code getSelectEditAddStudent() {
		return selectEditAddStudent;
	}
	public void setSelectEditAddStudent(Code selectEditAddStudent) {
		this.selectEditAddStudent = selectEditAddStudent;
	}
	public List<Student> getListUpload() {
		return listUpload;
	}
	public void setListUpload(List<Student> listUpload) {
		this.listUpload = listUpload;
	}
	public List<Student> getSelectAddStudent() {
		return selectAddStudent;
	}
	public void setSelectAddStudent(List<Student> selectAddStudent) {
		this.selectAddStudent = selectAddStudent;
	}
	
	
	
	
	
	
}
