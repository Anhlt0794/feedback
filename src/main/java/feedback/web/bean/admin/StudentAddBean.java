package feedback.web.bean.admin;
/**
 * Created by Anh on 01/19/2024.
 */
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import feedback.core.models.entities.Code;
import feedback.core.models.entities.Student;
import feedback.core.services.CodeServices;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.StudentServer;
import feedback.web.util.FacesUtil;

@ManagedBean(name = "stab")
@RequestScoped
public class StudentAddBean {
	@ManagedProperty("#{codeServices}")
	private CodeServices codeServices;
	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{entityManager}")
	private EntityManagerServices managerServices;
	@ManagedProperty("#{param.cid}")
	private int idCode;
	private Code selectEdit = new Code();
	private List<Code> codeViews = new ArrayList<Code>();
	private List<Student> uploads = new ArrayList<Student>();
	private List<Student> selectAdd = new ArrayList<Student>();
	private Student add = new Student();
	private Code selectEditTmp = new Code();
	private String visitListCode;
	private String visitInput;
	@PostConstruct
	public void init() {
		setCodeViews(codeServices.getAll());
		if (idCode != 0) {
			try {
				Code tmp = codeServices.getCodeByID(idCode);
				setSelectEdit(tmp);
			} catch (Exception e) {
				System.out.println("Loi param add student Bean");
			}

		} else {
			setSelectEdit(getCodeViews().get(0));
		}
		setVisitListFalse();
		setVisitInputTrue();
	}

	public void upload(FileUploadEvent event) {
		if (event.getFile() != null) {

			try {
				XSSFWorkbook workbook = new XSSFWorkbook(event.getFile()
						.getInputstream());
				if (workbook != null) {
					List<Code> ctmp = FacesUtil.readExcel(workbook);
					List<Student> students = new ArrayList<Student>();
					for (Code c : ctmp) {
						for (Student s : c.getStudent()) {
							if (!students.contains(s)) {
								students.add(s);
							}
						}
					}
					if (students.size() > 0) {
						setUploads(students);
						setVisitListTrue();
						setVisitInputFalse();

						info("Upload complete!", "File"
								+ event.getFile().getFileName());
					} else {
						warn("", "File excel not record student!");
					}
				}

			} catch (Exception e) {
				error("Error Excel Configuration",
						"Please fix excel correct configuration!");
			}
		}
	}

	public void addStudent() {
		if (uploads.size() > 0) {
			if (selectAdd.size() > 0) {
				for (Student sa : selectAdd) {
					Student s = studentServer.getByNo(sa.getStudentNo());
					if (s.getStudentID() > 0) {
						warn("",
								s.getStudentNo()
										+ " had in database! If you want motify it please choose edit Student. Thank!");
					} else {
						try {
							s.setCode(selectEdit);
							selectEdit.getStudent().add(s);
							managerServices.save(selectEdit);
							info("Success", s.getStudentNo()
									+ " insert database!");
						} catch (Exception e) {
							error("We are very sorry for this inconvenience",
									"Please try import student :"
											+ s.getStudentNo()
											+ " later. Thank!");
						}
					}
				}
			} else {
				info("", "Please select one more code in table!");
				return;
			}
		} else {
			if ("".equals(add.getStudentNo().trim())) {
				info("", "Please enter value ID!");
				return;
			} else if (studentServer.getByNo(add.getStudentNo()).getStudentID() > 0) {
				info("", "ID: " + add.getStudentNo()
						+ " had in database!Please try other value!");
				return;
			} else if ("".equals(add.getStudentName().trim())) {
				info("", "Please enter value Name!");
				return;
			} else if ("".equals(add.getStudentNo().trim())) {
				info("", "Please enter value ID!");
				return;
			}
			try {
				add.setCode(selectEdit);
				selectEdit.getStudent().add(add);
				managerServices.save(add);
				info("Success", add.getStudentNo() + " insert database!");
			} catch (Exception e) {
				error("We are very sorry for this inconvenience",
						"Please try import student :" + add.getStudentNo()
								+ " later. Thank!");
			}
		}
	}

	public String cancelStudent() {
		setAdd(new Student());
		setUploads(new ArrayList<Student>());
		setVisitListFalse();
		setVisitInputTrue();
		return null;
	}

	public void loadEditTmp() {
		Code c = new Code();
		c.setCodeNo(selectEdit.getCodeNo());
		c.setCodeName(selectEdit.getCodeName());
		c.setStudent(selectEdit.getStudent());
		setSelectEditTmp(c);
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

	public void setCodeServices(CodeServices codeServices) {
		this.codeServices = codeServices;
	}

	public int getIdCode() {
		return idCode;
	}

	public void setIdCode(int idCode) {
		this.idCode = idCode;
	}

	public Code getSelectEdit() {
		return selectEdit;
	}

	public void setSelectEdit(Code selectEdit) {
		this.selectEdit = selectEdit;
	}

	public List<Code> getCodeViews() {
		return codeViews;
	}

	public void setCodeViews(List<Code> codeViews) {
		this.codeViews = codeViews;
	}

	public List<Student> getSelectAdd() {
		return selectAdd;
	}

	public void setSelectAdd(List<Student> selectAdd) {
		this.selectAdd = selectAdd;
	}

	public Student getAdd() {
		return add;
	}

	public void setAdd(Student add) {
		this.add = add;
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

	public List<Student> getUploads() {
		return uploads;
	}

	public void setUploads(List<Student> uploads) {
		this.uploads = uploads;
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

	public Code getSelectEditTmp() {
		return selectEditTmp;
	}

	public void setSelectEditTmp(Code selectEditTmp) {
		this.selectEditTmp = selectEditTmp;
	}

}
