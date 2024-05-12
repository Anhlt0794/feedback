package feedback.web.bean.student;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import feedback.core.models.entities.Criterion;
import feedback.core.models.entities.Feedback;
import feedback.core.models.entities.Question;
import feedback.core.models.entities.Result;
import feedback.core.models.entities.Subject;
import feedback.core.models.entities.Teacher;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.ServicesCriterion;
import feedback.core.services.StudentServer;

@ManagedBean
@ViewScoped
public class AssessmentSubjectBean {

	private boolean skip;
	private Subject subject = new Subject();
	private Teacher teacher = new Teacher();
	private Result result = new Result();

	private Criterion criterion = new Criterion();
	private Feedback feedback = new Feedback();
	private List<Question> questions = new ArrayList<Question>();
	// List<Integer> answerContents = new ArrayList<Integer>();
	// List<Integer> answerMethods = new ArrayList<Integer>();
	// List<Integer> answerSkill = new ArrayList<Integer>();
	// List<Integer> answerRelationship = new ArrayList<Integer>();
	List<Feedback> listBackC = new ArrayList<Feedback>();
	List<Feedback> listBackM = new ArrayList<Feedback>();
	List<Feedback> listBackR = new ArrayList<Feedback>();
	List<Feedback> listBackS = new ArrayList<Feedback>();
	List<Feedback> listBack = new ArrayList<Feedback>();
	private Integer inputContents;
	private Integer inputMethods;
	private Integer inputSkills;
	private Integer inputRalationship;

	@ManagedProperty("#{studentServer}")
	private StudentServer studentServer;
	@ManagedProperty("#{loginBeanStu}")
	private LoginBeanStu stB;
	@ManagedProperty("#{subjectBean}")
	private SubjectBean sujB;
	@ManagedProperty("#{servicesCriterion}")
	private ServicesCriterion servicesCriterion;
	@ManagedProperty("#{entityManager}")
	private EntityManagerServices entityManagerServices;

	// @ManagedProperty("#resultServies")
	// private ResultServies resS;

	@PostConstruct
	public void init() {
		result.setCriterion(servicesCriterion.getCriterionActive());
		setFbC();
		setFbM();
		setFbR();
		setFbS();
		loadQuetion();
		result.setStudent(stB.getStudent());
		result.setTeacher(sujB.getTeacher());
		result.setSubject(sujB.getSubject());

		questions = studentServer.loadQuestion();
	}

	public void saveRatingC() {
		result.setFeedbacks(listBackC);
		result.setStatus("edit");

		entityManagerServices.update(result);
	}

	public void saveRatingM() {
		result.setFeedbacks(listBackM);
		result.setStatus("edit");

		entityManagerServices.update(result);
	}

	public void saveRatingR() {
		result.setFeedbacks(listBackR);
		result.setStatus("edit");

		entityManagerServices.update(result);
	}

	public void saveRatingS() {
		result.setFeedbacks(listBackS);
		result.setStatus("edit");

		entityManagerServices.update(result);
	}

	public void saveOpinion() {
		result.setResultOpinion(result.getResultOpinion());
		entityManagerServices.update(result);
	}

	public void setFbC() {
		List<Question> list = new ArrayList<Question>();
		//
		for (Question qc : result.getCriterion().getQuestions()) {
			if (qc.getQuestionType().equalsIgnoreCase("content")) {
				list.add(qc);
			}
		}
		int i = 1;
		for (Question q : list) {

			listBackC.add(new Feedback(0, i, q, result));
			i++;
		}
	}

	public void setFbM() {
		List<Question> list = new ArrayList<Question>();
		//
		for (Question qc : result.getCriterion().getQuestions()) {
			if (qc.getQuestionType().equalsIgnoreCase("method")) {
				list.add(qc);
			}
		}
		int i = 1;
		for (Question q : list) {

			listBackM.add(new Feedback(0, i, q, result));
			i++;
		}
	}

	public void setFbR() {
		List<Question> list = new ArrayList<Question>();
		//
		for (Question qc : result.getCriterion().getQuestions()) {
			if (qc.getQuestionType().equalsIgnoreCase("relationship")) {
				list.add(qc);
			}
		}
		int i = 1;
		for (Question q : list) {

			listBackR.add(new Feedback(0, i, q, result));
			i++;
		}
	}

	public void setFbS() {
		List<Question> list = new ArrayList<Question>();
		//
		for (Question qc : result.getCriterion().getQuestions()) {
			if (qc.getQuestionType().equalsIgnoreCase("skill")) {
				list.add(qc);
			}
		}
		int i = 1;
		for (Question q : list) {

			listBackS.add(new Feedback(0, i, q, result));
			i++;
		}
	}

	public void loadQuetion() {
		List<Question> list = new ArrayList<Question>();
		for (Question qc : result.getCriterion().getQuestions()) {
			list.add(qc);
		}
		int i = 1;
		for (Question q : list) {

			listBackS.add(new Feedback(0, i, q, result));
			i++;
		}
	}

	public void score() {

	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public SubjectBean getSujB() {
		return sujB;
	}

	public void setSujB(SubjectBean sujB) {
		this.sujB = sujB;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public StudentServer getStudentServer() {
		return studentServer;
	}

	public void setStudentServer(StudentServer studentServer) {
		this.studentServer = studentServer;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Integer getInputContents() {
		return inputContents;
	}

	public void setInputContents(Integer inputContents) {
		this.inputContents = inputContents;
	}

	public Integer getInputMethods() {
		return inputMethods;
	}

	public void setInputMethods(Integer inputMethods) {
		this.inputMethods = inputMethods;
	}

	public Integer getInputSkills() {
		return inputSkills;
	}

	public void setInputSkills(Integer inputSkills) {
		this.inputSkills = inputSkills;
	}

	public Integer getInputRalationship() {
		return inputRalationship;
	}

	public void setInputRalationship(Integer inputRalationship) {
		this.inputRalationship = inputRalationship;
	}

	public LoginBeanStu getStB() {
		return stB;
	}

	public void setStB(LoginBeanStu stB) {
		this.stB = stB;
	}

	public List<Feedback> getListBackC() {
		return listBackC;
	}

	public void setListBackC(List<Feedback> listBackC) {
		this.listBackC = listBackC;
	}

	public List<Feedback> getListBackM() {
		return listBackM;
	}

	public void setListBackM(List<Feedback> listBackM) {
		this.listBackM = listBackM;
	}

	public List<Feedback> getListBackR() {
		return listBackR;
	}

	public List<Feedback> getListBack() {
		return listBack;
	}

	public void setListBack(List<Feedback> listBack) {
		this.listBack = listBack;
	}

	public void setListBackR(List<Feedback> listBackR) {
		this.listBackR = listBackR;
	}

	public List<Feedback> getListBackS() {
		return listBackS;
	}

	public void setListBackS(List<Feedback> listBackS) {
		this.listBackS = listBackS;
	}

	public ServicesCriterion getServicesCriterion() {
		return servicesCriterion;
	}

	public void setServicesCriterion(ServicesCriterion servicesCriterion) {
		this.servicesCriterion = servicesCriterion;
	}

	public EntityManagerServices getEntityManagerServices() {
		return entityManagerServices;
	}

	public void setEntityManagerServices(
			EntityManagerServices entityManagerServices) {
		this.entityManagerServices = entityManagerServices;
	}

}
