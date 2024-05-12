package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Code;

/**
 * Created by Hien on 5/19/2015. <br/>
 * value=codeServices
 */
public interface CodeServices {
	public List<Code> getAll();
	public Code createCode(Code data);
	public Code getCodeByID(int id);
	public Code updateCode(Code data);
	public Code getCodeByNo(String no);
}
