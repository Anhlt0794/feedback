package feedback.web.bean.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import feedback.core.models.entities.Code;
import feedback.core.services.CodeServices;

@ManagedBean(name="cc")
public class CodeConverter implements Converter{
	@ManagedProperty("#{codeServices}")
	private CodeServices sv;
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Code ctmp = sv.getCodeByNo(value);
		if(ctmp!=null) return ctmp;
		return new Code();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		return ((Code)value).getCodeNo();
	}

	public CodeServices getSv() {
		return sv;
	}

	public void setSv(CodeServices sv) {
		this.sv = sv;
	}

	
	

}
