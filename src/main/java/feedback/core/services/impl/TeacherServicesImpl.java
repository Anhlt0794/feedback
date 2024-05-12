package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Teacher;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.TeacherServiecs;

@Service("tv")
public class TeacherServicesImpl implements TeacherServiecs {
	@Autowired
    private EntityChangeRepo repo;
	@Override
	public List<Teacher> getAll() {
		
		return (List<Teacher>) repo.queryAll(Teacher.class);
	}

	@Override
	public Teacher create(Teacher data) {
		// TODO Auto-generated method stub
		return repo.save(data);
	}

	@Override
	public Teacher getByID(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Teacher.class, id);
	}

	@Override
	public Teacher update(Teacher data) {
		// TODO Auto-generated method stub
		return repo.update(data);
	}

	@Override
	public Teacher getByNo(String no) {
		List<Teacher> teachers = (List<Teacher>) repo.findByCriteria(Teacher.class, Restrictions.eq("teacherNo", no));
		if(teachers.size()>0)return teachers.get(0);
		return new Teacher();
	}

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

}
