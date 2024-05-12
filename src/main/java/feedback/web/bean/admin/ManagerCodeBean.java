package feedback.web.bean.admin;
/**
 * Created by Hien on 5/19/2015.
 */
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import feedback.core.models.entities.Code;
import feedback.core.models.entities.Student;
import feedback.core.services.CodeServices;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.StudentServer;
import feedback.web.util.FacesUtil;

@ManagedBean
@SessionScoped
public class ManagerCodeBean {
	@ManagedProperty("#{codeServices}")
	private CodeServices codeServices;
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{entityManager}")
	private EntityManagerServices managerServices;
	private String id;
	private String name;
	private String nameFile;
	/**
	 * Code add use be for excel student upload
	 * */
	private List<Code> codesAdd = new ArrayList<Code>();
	private List<Code> codesView = new ArrayList<Code>();
	private List<Code> selectedCode = new ArrayList<Code>();
	private List<Code> selectedCodeView = new ArrayList<Code>();
 	private FileUpload fileUpload;
	private UploadedFile uploadedFile;
	private String visitListCode ;
	private String visitInput;
	private Code addNew = new Code();
	private Code selectCode = new Code();
	
	@PostConstruct
	public void init(){
		setCodesView(codeServices.getAll());
		setVisitListFalse();
		setVisitInputTrue();
	}
	public void addCode(){
		if(selectedCode.size()>0){
			try {
				for(Code c: getSelectedCode()){
					managerServices.save(c);
				}
				info("Success", "Data updated!");
			} catch (Exception e) {
				error("Add Code", "Code had in database!");
				System.out.println("first catch");
			}
		}
		
	}
	public CodeServices getCodeServices() {
		return codeServices;
	}
	public List<Student> existInDB(List<Student> students){
		return studentServer.existInDB(students);
	}
	public void showDialog(){
		RequestContext.getCurrentInstance().openDialog("login");
	}public String viewCode() {
		if(selectedCodeView.size()>0) 
		{
			return "viewCodeDetail";
			}
		info("", "Please select one more code in table!");
		return null;
	}
	public void upLoadFile(FileUploadEvent event ){	
		
		if(event.getFile()!= null){
			
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(event.getFile().getInputstream());
				if(workbook!=null){
					setCodesAdd(FacesUtil.readExcel(workbook));
					setVisitListTrue();
					setVisitInputFalse();
					
			
					   	
					   info("Upload complete!", "File"+ event.getFile().getFileName());
				}
					
			} catch (Exception e) {
					error("Error Excel Configuration", "Please fix excel correct configuration!");
			}
		}
	}
	
	public String getGender(int i){
		if(i==1) return "Male";
		return "Female";
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
	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public void setCodeServices(CodeServices codeServices) {
		this.codeServices = codeServices;
	}
	

	public List<Code> getCodesView() {
		return codesView;
	}

	public void setCodesView(List<Code> codesView) {
		this.codesView = codesView;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Code getAddNew() {
		return addNew;
	}

	public void setAddNew(Code addNew) {
		this.addNew = addNew;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Code> getCodesAdd() {
		return codesAdd;
	}
	public void setCodesAdd(List<Code> codesAdd) {
		this.codesAdd = codesAdd;
	}
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}
	public String getVisitListCode() {
		return visitListCode;
	}
	public void setVisitListCode(String visitListCode) {
		this.visitListCode = visitListCode;
	}
	public void setVisitListFalse(){
		setVisitListCode("display: none");
	}
	public void setVisitListTrue(){
		setVisitListCode("");
	}
	public void setVisitInputFalse(){
		setVisitInput("display: none");
	}
	public void setVisitInputTrue(){
		setVisitInput("");
	}
	public List<Code> getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(List<Code> selectedCode) {
		this.selectedCode = selectedCode;
	}
	public String getVisitInput() {
		return visitInput;
	}
	public void setVisitInput(String visitInput) {
		this.visitInput = visitInput;
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
	public Code getSelectCode() {
		return selectCode;
	}
	public void setSelectCode(Code selectCode) {
		this.selectCode = selectCode;
	}
	public List<Code> getSelectedCodeView() {
		return selectedCodeView;
	}
	public void setSelectedCodeView(List<Code> selectedCodeView) {
		this.selectedCodeView = selectedCodeView;
	}
	
	
}
