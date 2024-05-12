package feedback.core.models.entities;

/***********************************************************************
 * Module:  Teacher.java
 * Author:  Hien
 * Purpose: Defines the Class Teacher
 ***********************************************************************/

import java.io.Serializable;
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
import javax.persistence.OneToMany;
@Entity
public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long teacherID;
	@Column(nullable= false, unique= true)
	private String teacherNo;
	private String teacherName;
	private String teacherPass;
	private String teacherEmail;
	private int teacherGender;
	private Date teacherBirthDay;
	private String teacherAddress;
	private String teacherPhone;
	private String teacherPro;
	@OneToMany(mappedBy="teacher",cascade= CascadeType.ALL,fetch =FetchType.LAZY)
	private List<Result> result;
	@ManyToMany
	@JoinTable(name="Teacher_Subject",joinColumns=@JoinColumn(name="tacherID"),inverseJoinColumns=@JoinColumn(name="subjectID"))
	private List<Subject> subjects;

	public Teacher() {
	}

	public Teacher(String teacherNo, String teacherName, String teacherPass,
			String teacherEmail, int teacherGender, Date teacherBirthDay,
			String teacherAddress, String teacherPhone, String teacherPro,
			List<Subject> subjects) {
		super();
		this.teacherNo = teacherNo;
		this.teacherName = teacherName;
		this.teacherPass = teacherPass;
		this.teacherEmail = teacherEmail;
		this.teacherGender = teacherGender;
		this.teacherBirthDay = teacherBirthDay;
		this.teacherAddress = teacherAddress;
		this.teacherPhone = teacherPhone;
		this.teacherPro = teacherPro;
		this.subjects = subjects;
	}

	public long getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(long teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPass() {
		return teacherPass;
	}

	public void setTeacherPass(String teacherPass) {
		this.teacherPass = teacherPass;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public int getTeacherGender() {
		return teacherGender;
	}

	public void setTeacherGender(int teacherGender) {
		this.teacherGender = teacherGender;
	}

	public Date getTeacherBirthDay() {
		return teacherBirthDay;
	}

	public void setTeacherBirthDay(Date teacherBirthDay) {
		this.teacherBirthDay = teacherBirthDay;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	public String getTeacherPro() {
		return teacherPro;
	}

	public void setTeacherPro(String teacherPro) {
		this.teacherPro = teacherPro;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public String genderString(){
		if(this.teacherGender==1) return "Male";
		return "Female";
	}

	


}