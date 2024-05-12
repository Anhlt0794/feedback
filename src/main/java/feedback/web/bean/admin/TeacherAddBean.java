package feedback.web.bean.admin;
/**
 * Created by Hien on 5/19/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import feedback.core.models.entities.Teacher;
import feedback.core.services.SubjectServices;
import feedback.core.services.TeacherServiecs;
import feedback.web.util.FacesUtil;

@ManagedBean(name = "tab")
@ViewScoped
public class TeacherAddBean {
	@ManagedProperty("#{tv}")
	private TeacherServiecs services;
	@ManagedProperty("#{sv}")
	private SubjectServices servicesSubject;
	private String visitListCode;
	private String visitInput;
	private Teacher add = new Teacher();
	private List<Teacher> adds = new ArrayList<Teacher>();
	private List<Teacher> select = new ArrayList<Teacher>();
	private HashMap<String, Integer> gender ;
	@PostConstruct
	public void init() {
		gender = new HashMap<String, Integer>();
		gender.put("Male", 1);
		gender.put("Female", 1);
		setVisitListFalse();
		setVisitInputTrue();
	}

	public void add() {
		if (select.size() > 0) {
			for (Teacher s : getSelect()) {
				try {
					services.update(s);
					info("Success", s.getTeacherNo() + "updated!");
				} catch (Exception e) {
					error("Add Code", s.getTeacherNo() + " had in database!");
				}
			}
		} else {
			if (add.getTeacherNo().trim().equals("")) {
				warn("", "Please enter value ID!");
				return;
			} else if (add.getTeacherNo().trim().equals("")) {
				warn("", "Please enter value ID!");
				return;
			}
			try {
				services.update(add);
				info("Success", add.getTeacherNo() + " updated!");
			} catch (Exception e) {
				error("Add Code", add.getTeacherNo() + " had in database!");
			}
		}
	}

	public void upLoadFile(FileUploadEvent event) {

		if (event.getFile() != null) {

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(event.getFile().getInputstream());
				if (workbook != null) {
					setAdds(readExcelTeacher(workbook));
					setVisitListTrue();
					setVisitInputFalse();

					info("Upload complete!", "File"
							+ event.getFile().getFileName());
				}

			} catch (Exception e) {
				
				error("Error file excel",
						"Please check excel correct configuration! " + e);
			}
		}
	}

	private List<Teacher> readExcelTeacher(XSSFWorkbook workbook) {
		List<Teacher> rs = new ArrayList<Teacher>();
		 List<XSSFSheet> sheets  = FacesUtil.readExcelStudentMutliSheetToList(workbook);
		 for(XSSFSheet s: sheets){
			 rs.addAll(readExcelOneSheet(s));
		 }
		
		return rs;
	}

	public static List<Teacher> readExcelOneSheet(XSSFSheet sheet) {
 		Boolean read = false;

 		List<Teacher> rs = new ArrayList<Teacher>();
 		Iterator<Row> rowI = sheet.iterator();
		int culBeginRead = 0;
		while (rowI.hasNext()) {
			Row row = rowI.next();
			Iterator<Cell> iterator = row.cellIterator();
			if (!read) {
				readCell: while (iterator.hasNext()) {
					Cell cell = iterator.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getStringCellValue().trim()
								.equalsIgnoreCase("stt")) {
							read = true;
							culBeginRead = cell.getColumnIndex();
							break readCell;

						}

					}
				}
			} else {
				/*while (iterator.hasNext()) {
					Cell cell = iterator.next();
					if (culBeginRead == cell.getColumnIndex()) {
						Cell no = iterator.next();
						Cell name = iterator.next();
						Cell gender = iterator.next();
						Cell birthd = iterator.next();
						Cell address = iterator.next();
						Cell phone = iterator.next();
						
					}
				}*/

			}

		}
 		return rs;

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

	public Teacher getAdd() {
		return add;
	}

	public void setAdd(Teacher add) {
		this.add = add;
	}

	public List<Teacher> getAdds() {
		return adds;
	}

	public void setAdds(List<Teacher> adds) {
		this.adds = adds;
	}

	public List<Teacher> getSelect() {
		return select;
	}

	public void setSelect(List<Teacher> select) {
		this.select = select;
	}

	public TeacherServiecs getServices() {
		return services;
	}

	public void setServices(TeacherServiecs services) {
		this.services = services;
	}

	public SubjectServices getServicesSubject() {
		return servicesSubject;
	}

	public void setServicesSubject(SubjectServices servicesSubject) {
		this.servicesSubject = servicesSubject;
	}

	public HashMap<String, Integer> getGender() {
		return gender;
	}

	public void setGender(HashMap<String, Integer> gender) {
		this.gender = gender;
	}
	
	

}
