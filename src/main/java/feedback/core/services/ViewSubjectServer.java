package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Subject;

public interface ViewSubjectServer {
	public List<Subject> loadAllSubject();

	public Subject getByID(int id);
	public List<Subject> loadSubjectStu(long id);

}
