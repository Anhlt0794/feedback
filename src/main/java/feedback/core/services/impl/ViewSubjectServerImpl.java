package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Subject;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.ViewSubjectServer;

@Service("viewSubjectServer")
public class ViewSubjectServerImpl implements ViewSubjectServer {
	@Autowired
	private EntityChangeRepo repo;

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<Subject> loadAllSubject() {
		// TODO Auto-generated method stub
		return (List<Subject>) repo.queryAll(Subject.class);
	}

	@Override
	public Subject getByID(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Subject.class, id);
	}

	@Override
	public List<Subject> loadSubjectStu(long id) {
		// TODO Auto-generated method stub
		List<Subject> listSubject = (List<Subject>) repo.findByCriteria(Subject.class, Restrictions.eq("students.studentID", id));
		return listSubject;
	}

}
