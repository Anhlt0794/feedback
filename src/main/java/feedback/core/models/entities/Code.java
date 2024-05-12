/***********************************************************************
 * Module:  Code.java
 * Author:  Hien
 * Purpose: Defines the Class Code
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
import javax.persistence.OneToMany;

@Entity
public class Code implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codeID;

	@Column(nullable = false, unique = true)
	private String codeNo;

	@Column(nullable = false, unique = true)
	private String codeName;
	@OneToMany(mappedBy = "code", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Student> student;

	public Code() {
	}

	public Code(String codeNo, String codeName, List<Student> student) {
		this.codeNo = codeNo;
		this.codeName = codeName;
		this.student = student;
	}

	public int getCodeID() {
		return codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	@Column(nullable = false, unique = true)
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public void addStudent(List<Student> students) {
		for (Student t : students) {
			addStudent(t);
		}
	}

	public void addStudent(Student newStudent) {
		if (newStudent == null)
			return;
		if (this.student == null)
			this.student = new ArrayList<Student>();
		if (!this.student.contains(newStudent))
			this.student.add(newStudent);
		newStudent.setCode(this);
	}

	public void removeStudent(Student oldStudent) {
		if (oldStudent == null)
			return;
		if (this.student != null)
			if (this.student.contains(oldStudent))
				this.student.remove(oldStudent);
	}

	public void removeAllStudent() {
		if (student != null)
			student.clear();
	}

	@Override
	public String toString() {
		return "Code [codeID=" + codeID + ", codeNo=" + codeNo + ", codeName="
				+ codeName + ", student=" + student + "]";
	}

}