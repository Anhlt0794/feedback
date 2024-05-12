package feedback.core.services.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Pages;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.PagesServices;

@Service("pageServices")
public class PageServicesImpl implements PagesServices ,Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	    private EntityChangeRepo repo;
	@Override
	public Pages getPage(String id) {
		List<Pages> pages = (List<Pages>) repo.findByCriteria(Pages.class, Restrictions.eq("no",id));
		if(pages.size()>0)return pages.get(0);
		return new Pages();
	}
	@Override
	public List<Pages> getAllRootPages() {
		List<Pages> pages = (List<Pages>) repo.findByCriteria(Pages.class, Restrictions.eq("location","root"));
		return pages;
	}
	@Override
	public Pages getPage(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Pages.class, id);
	}
	public EntityChangeRepo getRepo() {
		return repo;
	}
	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}
	@Override
	public List<Pages> getAllPages() {
		
		return (List<Pages>) repo.queryAll(Pages.class);
	}
	

}
