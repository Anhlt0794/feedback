package feedback.core.services.impl;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.EntityManagerServices;

@Service("entityManager")
public class EntityManagerServicesImpl implements EntityManagerServices {
	@Autowired
	private EntityChangeRepo repo;


	@Override
	public <T, E> T getByID(Class<T> class1, E id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, E> Collection<T> findByCriteria(Class<T> class1, Criterion... ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, E> Collection<T> findByExample(Class<T> class1,
			E exampleInstance, String[] excludeProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Collection<T> findByCriteria(Class<T> class1, int firstResult,
			int maxResults, Criterion... criterion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int countByCriteria(Class<T> class1, Criterion... criterion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T, E> T findReference(Class<T> class1, E id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void refresh(T t) {
		// TODO Auto-generated method stub

	}



	@Override
	public <T> void delete(T t) {
		// TODO Auto-generated method stu

	}

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

	@Override
	public <T> Collection<T> queryAll(Class<T> class1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T save(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

}
