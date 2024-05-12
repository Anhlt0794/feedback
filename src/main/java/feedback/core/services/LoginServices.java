package feedback.core.services;

import feedback.core.models.entities.Manager;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Teacher;




/**
 * Created by Hien on 5/19/2015.\n
 * value=loginServices
 */
public interface LoginServices {
    public Manager loginManager(Manager manager);
    public Student loginStudent(Student student);
    public Teacher loginTeacher(Teacher teacher);
}
