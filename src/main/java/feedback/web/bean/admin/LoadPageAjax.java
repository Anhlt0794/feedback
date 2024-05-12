package feedback.web.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import feedback.core.models.entities.Pages;
import feedback.core.services.PagesServices;
@ManagedBean
@SessionScoped
public class LoadPageAjax implements Serializable{
	private static final long serialVersionUID = 1L;
	private Pages view = new Pages();
	private String pageId;
	private List<Pages> pages = new ArrayList<Pages>();
	private List<Pages> pagesRoot = new ArrayList<Pages>();
	
	@ManagedProperty("#{pageServices}")
	private PagesServices pagesServices;
	public PagesServices getPagesServices() {
		return pagesServices;
	}
	public void setPagesServices(PagesServices pagesServices) {
		this.pagesServices = pagesServices;
	}
	public Pages getView() {
		return view;
	}
	public void setView(Pages view) {
		this.view = view;
	}
	@PostConstruct
	public void inti(){
		try {
			view = pagesServices.getPage("dashboard");
			pages = pagesServices.getAllPages();
			pagesRoot = pagesServices.getAllRootPages();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}
	public Pages viewPage(String id){
		return pagesServices.getPage(id);
	}
	public Pages page(String id){
		for(Pages p : pages){
			if(p.getNo().equalsIgnoreCase(id))return p;
		}
		return new Pages();
		
	}
	public void changeViewPage(String id){
		setView(pagesServices.getPage(id));
	}public void changeViewPage(Pages id){
		setView(id);
	}
	public List<Pages> getPages() {
		return pages;
	}
	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public List<Pages> getPagesRoot() {
		return pagesRoot;
	}
	public void setPagesRoot(List<Pages> pagesRoot) {
		this.pagesRoot = pagesRoot;
	}
	
}
