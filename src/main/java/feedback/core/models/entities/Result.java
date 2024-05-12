package feedback.core.models.entities;

/***********************************************************************
 * Module:  Result.java
 * Author:  Hien
 * Purpose: Defines the Class Result
 ***********************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resultID;
	private int resultDuration;
	private String resultOpinion;
	private String resultType;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "studentID")
	private Student student;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherID")
	private Teacher teacher;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "criterionID")
	private Criterion criterion;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "subjectID")
	private Subject subject;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy= "result")
	private List<Feedback> feedbacks;
	private String status;

	public Result() {
		// TODO Auto-generated constructor stub
	}

	public Result(int resultDuration, String resultOpinion, String resultType,
			Student student, Teacher teacher, Criterion criterion,
			Subject subject, List<Feedback> feedbacks, String status) {
		this.resultDuration = resultDuration;
		this.resultOpinion = resultOpinion;
		this.resultType = resultType;
		this.student = student;
		this.teacher = teacher;
		this.criterion = criterion;
		this.subject = subject;
		this.feedbacks = feedbacks;
		this.status = status;
	}

	public long getResultID() {
		return resultID;
	}

	public void setResultID(long resultID) {
		this.resultID = resultID;
	}

	public int getResultDuration() {
		return resultDuration;
	}

	public void setResultDuration(int resultDuration) {
		this.resultDuration = resultDuration;
	}

	public String getResultOpinion() {
		return resultOpinion;
	}

	public void setResultOpinion(String resultOpinion) {
		this.resultOpinion = resultOpinion;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void addFeedback(List<Feedback> feedbacks){
		if(this.feedbacks ==null){
			this.feedbacks = new ArrayList<Feedback>();
		}else{
			this.feedbacks.addAll(feedbacks);
		}
	}
	
}