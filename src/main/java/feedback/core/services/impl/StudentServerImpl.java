package feedback.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Question;
import feedback.core.models.entities.Result;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Subject;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.StudentServer;

@Service("studentServer")
public class StudentServerImpl implements StudentServer {
	@Autowired
	private EntityChangeRepo repo;

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return (List<Student>) repo.queryAll(Student.class);
	}

	@Override
	public Student save(Student stu) {
		// TODO Auto-generated method stub
		return repo.save(stu);
	}

	@Override
	public Student update(Student stu) {
		// TODO Auto-generated method stub
		return repo.update(stu);
	}

	@Override
	public Student getByID(long id) {
		// TODO Auto-generated method stub
		return repo.getByID(Student.class, id);
	}

	@Override
	public List<Question> loadQuestion() {
		// TODO Auto-generated method stub
		return (List<Question>) repo.queryAll(Question.class);
	}


	@Override
	public Result saveResult(Result rs) {
		// TODO Auto-generated method stub
		return repo.save(rs);
	}

	@Override
	public Result viewResult(Result rs) {
		// TODO Auto-generated method stub
		return null;
	}

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

	@Override
	public List<Question> loadQuestionByType(String type) {
		// TODO Auto-generated method stub
		List<Question> quetionType = (List<Question>) repo.findByCriteria(Question.class, Restrictions.eq("questionType", type));
		return quetionType;
	}

	@Override
	public Subject getByIDSubj(long id) {
		// TODO Auto-generated method stub
		Subject subject = repo.getByID(Subject.class, id);
		return subject;
	}

	@Override
	public List<Student> existInDB(List<Student> list) {
		List<Student> result = new ArrayList<Student>();
		for(Student t: list){
			Student rs = existInDB(t);
			if(rs!=null) result.add(rs);
		}
		return result;
	}

	@Override
	public Student existInDB(Student student) {
		List<Student> list = (List<Student>) repo.findByCriteria(Student.class, Restrictions.eq("studentNo", student.getStudentNo()));
		if(list.size()>0)return list.get(0);
		return new Student();
	}

	@Override
	public Student getByNo(String no) {
		List<Student> students = (List<Student>) repo.findByCriteria(Student.class, Restrictions.eq("studentNo", no));
		if(students.size()>0)return students.get(0);
		return new Student();
	}

}
