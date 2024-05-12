package feedback.web.bean.admin;
/**
 * Created by Hien on 5/19/2015.
 */
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import feedback.core.models.entities.Manager;
import feedback.core.services.ManagerServices;


@ManagedBean
public class ManagerBean {
	@ManagedProperty(value = "#{managerServices}")
	private ManagerServices impl;
	private List<Manager> list;
	@PostConstruct
	public void init(){
		list = impl.getAll();
	}
	public List<Manager> getList() {
		return list;
	}

	public void setList(List<Manager> list) {
		this.list = list;
	}
	public ManagerServices getImpl() {
		return impl;
	}
	public void setImpl(ManagerServices impl) {
		this.impl = impl;
	}
	
	
	
	
	
}
