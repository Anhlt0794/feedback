package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Teacher;

/**
 * Created by Hien on 5/19/2015. <br/>
 * value=tv
 */
public interface TeacherServiecs {
	public List<Teacher> getAll();
	public Teacher create(Teacher data);
	public Teacher getByID(int id);
	public Teacher update(Teacher data);
	public Teacher getByNo(String no);
}
