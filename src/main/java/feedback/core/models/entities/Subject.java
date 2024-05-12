/***********************************************************************
 * Module:  Course.java
 * Author:  Hien
 * Purpose: Defines the Class Course
 ***********************************************************************/
package feedback.core.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Subject implements Serializable {

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", subjectNo=" + subjectNo
				+ ", subjectName=" + subjectName + ", teacher=" + teacher
				+ ", result=" + result + "]";
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subjectID;
	@Column(nullable= false, unique= true)
	private String subjectNo;
	private String subjectName;
	@ManyToMany(mappedBy="subjects", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch= FetchType.LAZY)
	private List<Teacher> teacher;
	@OneToMany(mappedBy="subject", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },fetch = FetchType.LAZY)
	private List<Result> result;
	@ManyToMany(mappedBy="subjects" ,cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } ,fetch = FetchType.LAZY)
	private List<Student> students;
	public Subject() {

	}
	
	public Subject(String subjectNo, String subjectName) {
		super();
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
	}

	public void addStudent(Student student){
		if(this.students == null){
			students = new ArrayList<Student>();
		}else{
			this.students.add(student);
		}
	}
	public long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(long subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}