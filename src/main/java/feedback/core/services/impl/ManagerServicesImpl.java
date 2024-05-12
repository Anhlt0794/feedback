package feedback.core.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feedback.core.models.entities.Manager;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.ManagerServices;

/**
 * Created by Hien on 5/19/2015.
 */
@Service("managerServices")
public class ManagerServicesImpl implements ManagerServices, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 282925368811257308L;
	@Autowired
    private EntityChangeRepo dao;
    
    public EntityChangeRepo getDao() {
		return dao;
	}

	public void setDao(EntityChangeRepo dao) {
		this.dao = dao;
	}

	@Transactional
    public List<Manager> getAll() {
        return (List<Manager>) dao.queryAll(Manager.class);
    }

    public Manager create(Manager manager) {
        dao.save(manager);
        return manager;
    }
    
}
