package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Code;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.CodeServices;

@Service("codeServices")
public class CodeServicesImpl implements CodeServices{
	@Autowired
    private EntityChangeRepo repo;

	@Override
	public List<Code> getAll() {
		// TODO Auto-generated method stub
		return (List<Code>) repo.queryAll(Code.class);
	}

	@Override
	public Code createCode(Code data) {
		// TODO Auto-generated method stub
		return repo.save(data);
	}

	@Override
	public Code getCodeByID(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Code.class, id);
	}

	@Override
	public Code updateCode(Code data) {
		// TODO Auto-generated method stub
		return repo.update(data);
	}

	@Override
	public Code getCodeByNo(String no) {
		List<Code> codes = (List<Code>) repo.findByCriteria(Code.class, Restrictions.eq("codeNo", no));
		if(codes.size()>0)return codes.get(0);
		return new Code();
	}
	

}
