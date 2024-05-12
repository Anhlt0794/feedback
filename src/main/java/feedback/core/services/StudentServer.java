package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Question;
import feedback.core.models.entities.Result;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Subject;
/**
 * Created by Nhat. <br/>
 * value=studentServer
 */

public interface StudentServer {
	public List<Student> getAll();

	public Student save(Student stu);
	public List<Student> existInDB(List<Student> list);
	public Student existInDB(Student student);
	public Student update(Student stu);
	public Student getByID(long id);
	public Student getByNo(String no);
	public List<Question> loadQuestion();
	public List<Question> loadQuestionByType(String type);
	public Result saveResult(Result rs);
	public Result viewResult(Result rs);
	public Subject getByIDSubj(long id);
	
	

}
