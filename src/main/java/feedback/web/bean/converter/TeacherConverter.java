package feedback.web.bean.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import feedback.core.models.entities.Teacher;
import feedback.core.services.TeacherServiecs;

@ManagedBean(name="ct")
public class TeacherConverter implements Converter{
	@ManagedProperty("#{tv}")
	private TeacherServiecs sv;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Teacher tmp = sv.getByNo(value);
		if(tmp!=null) return tmp;
		return new Teacher();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return ((Teacher)value).getTeacherNo();
	}

	public TeacherServiecs getSv() {
		return sv;
	}

	public void setSv(TeacherServiecs sv) {
		this.sv = sv;
	}

	



	
	

}
