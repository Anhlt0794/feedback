package feedback.core.models.entities;

/***********************************************************************
 * Module:  Question.java
 * Author:  Hien
 * Purpose: Defines the Class Question
 ***********************************************************************/

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long questionID;
	@Column(nullable = false, unique = true)
	private String questionTitle;
	private int questionDuration;
	private String questionType;
	private int available;
	@ManyToMany(mappedBy = "questions", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<Criterion> criterion;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy= "question")
	private List<Feedback> feedbacks;
	

	public Question() {
		// TODO Auto-generated constructor stub
	}

	public long getQuestionID() {
		return questionID;
	}

	public void setQuestionID(long questionID) {
		this.questionID = questionID;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public int getQuestionDuration() {
		return questionDuration;
	}

	public void setQuestionDuration(int questionDuration) {
		this.questionDuration = questionDuration;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public List<Criterion> getCriterion() {
		return criterion;
	}

	public void setCriterion(List<Criterion> criterion) {
		this.criterion = criterion;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}