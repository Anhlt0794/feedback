package feedback.core.services;



import java.util.List;

import feedback.core.models.entities.Manager;


/**
 * Created by Hien on 5/19/2015.
 * ManagerProperty: value="managerServices"
 */
public interface ManagerServices {
    public List<Manager> getAll();
    public Manager create(Manager manager);
}
