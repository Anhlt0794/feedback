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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import feedback.core.models.entities.Code;
import feedback.core.services.CodeServices;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.StudentServer;
import feedback.web.util.FacesUtil;

@ManagedBean(name = "acb")
@ViewScoped
public class CodeAddBean {
	@ManagedProperty("#{codeServices}")
	private CodeServices codeServices;
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{entityManager}")
	private EntityManagerServices managerServices;
	private Code addNew = new Code();
	private String visitListCode;
	private String visitInput;
	private List<Code> codesAdd = new ArrayList<Code>();
	private List<Code> selectedCode = new ArrayList<Code>();
	@PostConstruct
	public void init() {
		
		setVisitListFalse();
		setVisitInputTrue();
	}
	public void addCode(){
		if(selectedCode.size()>0){
			
				for(Code c: getSelectedCode()){
					try{
					managerServices.save(c);
					info("Success", c.getCodeNo() + "updated!");
					} catch (Exception e) {
						error("Add Code", c.getCodeNo() + " had in database!");
						System.out.println("first catch");			
					}
					
				}
		}else{
			if(codesAdd.size()>0){
				info("", "Please select one more code in table!");
			}else{
				if("".equals(addNew.getCodeNo().trim())){
					info("", "Please enter value Code ID!");
				}else 
					if("".equals(addNew.getCodeName().trim())){
						info("", "Please enter value Code Name!");
					}else{
						try{
							managerServices.save(addNew);
							info("Success", addNew.getCodeNo() + "updated!");
							setAddNew(new Code());
							} catch (Exception e) {
								error("Add Code", addNew.getCodeNo() + " had in database!");
							}
					}
			}
		}
		
	}
	public void upLoadFile(FileUploadEvent event) {

		if (event.getFile() != null) {

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(event.getFile()
						.getInputstream());
				if (workbook != null) {
					setCodesAdd(FacesUtil.readExcel(workbook));
					setVisitListTrue();
					setVisitInputFalse();

					info("Upload complete!", "File"
							+ event.getFile().getFileName());
				}

			} catch (Exception e) {
				error("Error Excel Configuration",
						"Please fix excel correct configuration!");
			}
		}
	}
	public String cancel(){
		setAddNew(new Code());
		setCodesAdd(new ArrayList<Code>());

		setVisitListFalse();
		setVisitInputTrue();
		return null;
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

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
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

	public List<Code> getCodesAdd() {
		return codesAdd;
	}

	public void setCodesAdd(List<Code> codesAdd) {
		this.codesAdd = codesAdd;
	}

	public Code getAddNew() {
		return addNew;
	}

	public void setAddNew(Code addNew) {
		this.addNew = addNew;
	}

	public List<Code> getSelectedCode() {
		return selectedCode;
	}

	public void setSelectedCode(List<Code> selectedCode) {
		this.selectedCode = selectedCode;
	}
	

}
