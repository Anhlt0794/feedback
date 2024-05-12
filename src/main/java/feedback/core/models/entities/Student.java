package feedback.core.models.entities;

/***********************************************************************
 * Module:  Student.java
 * Author:  Hien
 * Purpose: Defines the Class Student
 ***********************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentID;
	@Column(unique = true, nullable = false)
	private String studentNo;
	@Column(nullable = false)
	private String studentName;
	private String studentPass;
	private String studentEmail;
	/**
	 * @Male: 1
	 * @Female:0
	 * */
	private int studentGender;
	private Date studentBirthDay;
	private String studentAddress;
	private String studentPhone;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "codeID")
	private Code code;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Result> results;
	@ManyToMany
	@JoinTable(name="Student_Subject",joinColumns=@JoinColumn(name="studentID"),inverseJoinColumns=@JoinColumn(name="subjectID"))
	private List<Subject> subjects;

	public Student() {
	}

	public Student(String studentNo, String studentName, String studentPass,
			String studentEmail, int studentGender, Date studentBirthDay,
			String studentAddress, String studentPhone) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentPass = studentPass;
		this.studentEmail = studentEmail;
		this.studentGender = studentGender;
		this.studentBirthDay = studentBirthDay;
		this.studentAddress = studentAddress;
		this.studentPhone = studentPhone;
	}

	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPass() {
		return studentPass;
	}

	public void setStudentPass(String studentPass) {
		this.studentPass = studentPass;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public int getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(int studentGender) {
		this.studentGender = studentGender;
	}

	public Date getStudentBirthDay() {
		return studentBirthDay;
	}

	public void setStudentBirthDay(Date studentBirthDay) {
		this.studentBirthDay = studentBirthDay;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public void addSubject(Subject subject) {
		if(this.subjects== null){
			subjects = new ArrayList<Subject>();
		}else{
			subjects.add(subject);
			subject.addStudent(this);
		}
	}

	public void addSubject(List<Subject> subjects) {
		for(Subject s: subjects){
			addSubject(s);
		}
	}
	public String genderString(){
		if(this.studentGender==1) return "Male";
		return "Female";
	}

	@Override
	public String toString() {
		return "Student [studentID=" + studentID + ", studentNo=" + studentNo
				+ ", studentName=" + studentName + ", studentPass="
				+ studentPass + ", studentEmail=" + studentEmail
				+ ", studentGender=" + studentGender + ", studentBirthDay="
				+ studentBirthDay + ", studentAddress=" + studentAddress
				+ ", studentPhone=" + studentPhone + ", results=" + results
				+ "]";
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}