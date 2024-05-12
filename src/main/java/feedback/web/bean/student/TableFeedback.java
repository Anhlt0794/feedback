package feedback.web.bean.student;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Question;
import feedback.core.models.entities.Subject;
import feedback.core.models.entities.Teacher;

@ManagedBean
@SessionScoped
public class TableFeedback {
	private Subject sub = new Subject();
	private Teacher tec = new Teacher();
	private List<Question> questions = new ArrayList<Question>();
	private String feedbackMore;

	@PostConstruct
	public void init() {
		// todo something
	}

	public Subject getSub() {
		return sub;
	}

	public void setSub(Subject sub) {
		this.sub = sub;
	}

	public Teacher getTec() {
		return tec;
	}

	public void setTec(Teacher tec) {
		this.tec = tec;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getFeedbackMore() {
		return feedbackMore;
	}

	public void setFeedbackMore(String feedbackMore) {
		this.feedbackMore = feedbackMore;
	}

}
