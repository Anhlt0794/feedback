package feedback.web.bean.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Feedback;
import feedback.core.models.entities.Result;
import feedback.core.services.ResultServies;

@ManagedBean
@SessionScoped
public class TeacherBean {
	private Result result = new Result();
	private Feedback feedback = new Feedback();
	private double percent1;
	private double percent2;
	private double percent3;
	private double percent4;
	private double percent5;
	private int idqt = 1;

	private List<Feedback> listFeedback5 = new ArrayList<Feedback>();
	private List<Feedback> listFeedback4 = new ArrayList<Feedback>();
	private List<Feedback> listFeedback3 = new ArrayList<Feedback>();
	private List<Feedback> listFeedback2 = new ArrayList<Feedback>();
	private List<Feedback> listFeedback1 = new ArrayList<Feedback>();
	private List<Feedback> listFeedbackT = new ArrayList<Feedback>();

	@ManagedProperty("#{loginTeacherBean}")
	private LoginTeacherBean stT;
	@ManagedProperty("#{resultServies}")
	private ResultServies resultServies;

	@PostConstruct
	public void init() {
		setListFeedbackT(resultServies.loadFeedBack());
	}

	public void getFeedbackByIDQt(long id) {
		List<Feedback> listFeedback = new ArrayList<Feedback>();
		for (Feedback fb : result.getFeedbacks()) {
			if (fb.getQuestion().getQuestionID() == id) {
				listFeedback.add(fb);
			}
		}
		for (Feedback f : listFeedback) {
			if (f.getAnswer() == 5)
				listFeedback5.add(f);
			else if (f.getAnswer() == 4)
				listFeedback4.add(f);
			else if (f.getAnswer() == 3)
				listFeedback3.add(f);
			else if (f.getAnswer() == 2)
				listFeedback2.add(f);
			else if (f.getAnswer() == 1)
				listFeedback1.add(f);
		}
	}

	public void cc() {
		int sumFb = listFeedbackT.size();
		int sum5 = listFeedback5.size();
		int sum4 = listFeedback4.size();
		int sum3 = listFeedback3.size();
		int sum2 = listFeedback2.size();
		int sum1 = listFeedback1.size();

		percent5 = sum5 / sumFb * 100;
		percent4 = sum4 / sumFb * 100;
		percent3 = sum3 / sumFb * 100;
		percent2 = sum2 / sumFb * 100;
		percent1 = sum1 / sumFb * 100;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public double getPercent1() {
		return percent1;
	}

	public void setPercent1(double percent1) {
		this.percent1 = percent1;
	}

	public double getPercent2() {
		return percent2;
	}

	public void setPercent2(double percent2) {
		this.percent2 = percent2;
	}

	public double getPercent3() {
		return percent3;
	}

	public void setPercent3(double percent3) {
		this.percent3 = percent3;
	}

	public double getPercent4() {
		return percent4;
	}

	public void setPercent4(double percent4) {
		this.percent4 = percent4;
	}

	public double getPercent5() {
		return percent5;
	}

	public void setPercent5(double percent5) {
		this.percent5 = percent5;
	}

	public List<Feedback> getListFeedback5() {
		return listFeedback5;
	}

	public void setListFeedback5(List<Feedback> listFeedback5) {
		this.listFeedback5 = listFeedback5;
	}

	public List<Feedback> getListFeedback4() {
		return listFeedback4;
	}

	public void setListFeedback4(List<Feedback> listFeedback4) {
		this.listFeedback4 = listFeedback4;
	}

	public List<Feedback> getListFeedback3() {
		return listFeedback3;
	}

	public void setListFeedback3(List<Feedback> listFeedback3) {
		this.listFeedback3 = listFeedback3;
	}

	public List<Feedback> getListFeedback2() {
		return listFeedback2;
	}

	public void setListFeedback2(List<Feedback> listFeedback2) {
		this.listFeedback2 = listFeedback2;
	}

	public List<Feedback> getListFeedback1() {
		return listFeedback1;
	}

	public void setListFeedback1(List<Feedback> listFeedback1) {
		this.listFeedback1 = listFeedback1;
	}

	public List<Feedback> getListFeedbackT() {
		return listFeedbackT;
	}

	public void setListFeedbackT(List<Feedback> listFeedbackT) {
		this.listFeedbackT = listFeedbackT;
	}

	public int getIdqt() {
		return idqt;
	}

	public void setIdqt(int idqt) {
		this.idqt = idqt;
	}

	public ResultServies getResultServies() {
		return resultServies;
	}

	public void setResultServies(ResultServies resultServies) {
		this.resultServies = resultServies;
	}

	public LoginTeacherBean getStT() {
		return stT;
	}

	public void setStT(LoginTeacherBean stT) {
		this.stT = stT;
	}

}
