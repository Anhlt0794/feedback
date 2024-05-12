package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Manager;
import feedback.core.models.entities.Student;
import feedback.core.models.entities.Teacher;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.LoginServices;



/**
 * Created by Hien on 5/19/2015.
 */
@Service("loginServices")
public class LoginServicesImpl implements LoginServices {
    @Autowired
    private EntityChangeRepo repo;
    public Manager loginManager(Manager manager) {
        List<Manager> managers = (List<Manager>) repo.findByCriteria(Manager.class,
                Restrictions.and(
                		Restrictions.or(
                				Restrictions.eq("managerNo", manager.getManagerNo()),
                				Restrictions.eq("managerEmail", manager.getManagerEmail()))
                		,
                        Restrictions.eq("managerPass", manager.getManagerPass())));
        if(managers.size()>0)return managers.get(0);
        return new Manager();
    }
    public Student loginStudent(Student student) {
        List<Student> students = (List<Student>) repo.findByCriteria(
        		Student.class, Restrictions.and(
        				Restrictions.or(
        						Restrictions.eq("studentNo", student.getStudentNo()),
        						Restrictions.eq("studentEmail", student.getStudentEmail()))
        				,
        				Restrictions.eq("studentPass", student.getStudentPass())
        				)
        		);
        if(students.size()>0)return students.get(0);
        return new Student();
    }

    public Teacher loginTeacher(Teacher teacher) {
        List<Teacher> teachers = (List<Teacher>) repo.findByCriteria(Teacher.class,
        		Restrictions.and(
        				Restrictions.or(Restrictions.eq("teacherNo", teacher.getTeacherNo()),
        						Restrictions.eq("teacherEmail", teacher.getTeacherEmail()))
        				,
        				Restrictions.eq("teacherPass", teacher.getTeacherPass())));
        if(teachers.size()>0)return teachers.get(0);
        return new Teacher();
    }

	
}
