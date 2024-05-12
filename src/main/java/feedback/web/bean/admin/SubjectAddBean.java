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

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import feedback.core.models.entities.Subject;
import feedback.core.services.SubjectServices;
import feedback.web.util.FacesUtil;

@ManagedBean(name="sab")
@ViewScoped
public class SubjectAddBean {
	@ManagedProperty("#{sv}")
	private SubjectServices services;
	private Subject add = new Subject();
	private String visitListCode;
	private String visitInput;
	private List<Subject> adds = new ArrayList<Subject>();
	private List<Subject> select = new ArrayList<Subject>();
	 
	@PostConstruct
	public void init() {
		setVisitListFalse();
		setVisitInputTrue();
	}
	
	public void add(){
		if(select.size()>0){
			for(Subject s: getSelect()){
				try{
					services.update(s);
					info("Success", s.getSubjectNo() + "updated!");
				} catch (Exception e) {
					error("Add Code", s.getSubjectNo() + " had in database!");
				}
			}
		}else{
			if(add.getSubjectNo().trim().equals("")){
				warn("", "Please enter value ID!");
				return;
			}else if(add.getSubjectNo().trim().equals("")){
					warn("", "Please enter value ID!");
					return;
			}
			try{
				services.update(add);
				info("Success", add.getSubjectNo() + " updated!");
			} catch (Exception e) {
				error("Add Code", add.getSubjectNo() + " had in database!");
			}
		}
	}	
	public void upLoadFile(FileUploadEvent event) {

		if (event.getFile() != null) {

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(event.getFile()
						.getInputstream());
				if (workbook != null) {
					setAdds(FacesUtil.readExcelSubject(workbook));
					setVisitListTrue();
					setVisitInputFalse();

					info("Upload complete!", "File"
							+ event.getFile().getFileName());
				}

			} catch (Exception e) {
				error("Error file excel",
						"Please check excel correct configuration!");
			}
		}
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
	public SubjectServices getServices() {
		return services;
	}

	public void setServices(SubjectServices services) {
		this.services = services;
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

	public synchronized List<Subject> getAdds() {
		return adds;
	}

	public synchronized void setAdds(List<Subject> adds) {
		this.adds = adds;
	}

	public synchronized List<Subject> getSelect() {
		return select;
	}

	public synchronized void setSelect(List<Subject> select) {
		this.select = select;
	}

	public Subject getAdd() {
		return add;
	}

	public void setAdd(Subject add) {
		this.add = add;
	}
	
}
