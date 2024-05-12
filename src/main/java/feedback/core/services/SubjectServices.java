package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Subject;

public interface SubjectServices {
	public List<Subject> getAll();
	public Subject create(Subject data);
	public Subject getByID(int id);
	public Subject update(Subject data);
	public Subject getByNo(String no);
}
