package feedback.core.models.entities;

/***********************************************************************
 * Module:  Criterion.java
 * Author:  Hien
 * Purpose: Defines the Class Criterion
 ***********************************************************************/

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Criterion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long criterionID;
	private String criterionNo;
	private String criterionNam;
	private int criterionPosition;
	private int year;
	private int half;
	private int criterionActive;
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch =FetchType.LAZY)
	@JoinTable(name="Criterion_Question",joinColumns=@JoinColumn(name="criterionID"),inverseJoinColumns=@JoinColumn(name="questionID"))
	private List<Question> questions;
	@OneToMany(mappedBy="criterion",cascade= CascadeType.ALL,fetch =FetchType.LAZY)
	private List<Result> result;

	public Criterion() {
		// TODO Auto-generated constructor stub
	}

	public long getCriterionID() {
		return criterionID;
	}

	public void setCriterionID(long criterionID) {
		this.criterionID = criterionID;
	}

	public String getCriterionNam() {
		return criterionNam;
	}

	public void setCriterionNam(String criterionNam) {
		this.criterionNam = criterionNam;
	}

	public int getCriterionPosition() {
		return criterionPosition;
	}

	public void setCriterionPosition(int criterionPosition) {
		this.criterionPosition = criterionPosition;
	}

	public int getCriterionActive() {
		return criterionActive;
	}

	public void setCriterionActive(int criterionActive) {
		this.criterionActive = criterionActive;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHalf() {
		return half;
	}

	public void setHalf(int half) {
		this.half = half;
	}

	public String getCriterionNo() {
		return criterionNo;
	}

	public void setCriterionNo(String criterionNo) {
		this.criterionNo = criterionNo;
	}
	

}