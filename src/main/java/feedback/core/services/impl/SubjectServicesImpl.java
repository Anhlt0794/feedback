package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Subject;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.SubjectServices;

@Service("sv")
public class SubjectServicesImpl implements SubjectServices{
	@Autowired
    private EntityChangeRepo repo;
	@Override
	public List<Subject> getAll() {
		
		return (List<Subject>) repo.queryAll(Subject.class);
	}

	@Override
	public Subject create(Subject data) {
		// TODO Auto-generated method stub
		return repo.save(data);
	}

	@Override
	public Subject getByID(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Subject.class, id);
	}

	@Override
	public Subject update(Subject data) {
		// TODO Auto-generated method stub
		return repo.update(data);
	}

	@Override
	public Subject getByNo(String no) {
		List<Subject> subjects = (List<Subject>) repo.findByCriteria(Subject.class, Restrictions.eq("subjectNo", no));
		if(subjects.size()>0)return subjects.get(0);
		return new Subject() ;
	}

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

}
