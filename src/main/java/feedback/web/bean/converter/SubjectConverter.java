package feedback.web.bean.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import feedback.core.models.entities.Subject;
import feedback.core.services.SubjectServices;
@ManagedBean(name="cs")
public class SubjectConverter implements Converter{
	@ManagedProperty("#{sv}")
	private SubjectServices sv;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Subject tmp = sv.getByNo(value);
		if(tmp!=null) return tmp;
		return new Subject();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return ((Subject)value).getSubjectNo();
	}

	public synchronized SubjectServices getSv() {
		return sv;
	}

	public synchronized void setSv(SubjectServices sv) {
		this.sv = sv;
	}



	
	

}
