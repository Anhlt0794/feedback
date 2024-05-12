package feedback.core.models.entities;

/***********************************************************************
 * Module:  Manager.java
 * Author:  Hien
 * Purpose: Defines the Class Manager
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Manager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int managerID;
	@Column(nullable= false, unique= true)
	private String managerNo;
	private String managerName;
	private String managerPass;
	private String managerEmail;
	private String managerGender;
	private Date managerBirthDay;
	private String managerPhone;
	private int managerLevel;
	private String managerAddress;
	private byte[] avatar;
	public Manager() {
	}
	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPass() {
		return managerPass;
	}

	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerGender() {
		return managerGender;
	}

	public void setManagerGender(String managerGender) {
		this.managerGender = managerGender;
	}

	public Date getManagerBirthDay() {
		return managerBirthDay;
	}

	public void setManagerBirthDay(Date managerBirthDay) {
		this.managerBirthDay = managerBirthDay;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public int getManagerLevel() {
		return managerLevel;
	}

	public void setManagerLevel(int managerLevel) {
		this.managerLevel = managerLevel;
	}

	public String getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	
}