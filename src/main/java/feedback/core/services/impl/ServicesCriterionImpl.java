package feedback.core.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Criterion;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.ServicesCriterion;

@Service("servicesCriterion")
public class ServicesCriterionImpl implements ServicesCriterion {
	@Autowired
	private EntityChangeRepo repo;

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

	@Override
	public Criterion getCriterionActive() {
		// TODO Auto-generated method stub
		List<Criterion> list = (List<Criterion>) repo.findByCriteria(
				Criterion.class, Restrictions.eq("criterionActive", 1));
		if (list != null) {
			return list.get(0);
		}
		return new Criterion();
	}

	@Override
	public List<Criterion> getAll() {
		// TODO Auto-generated method stub
		return (List<Criterion>) repo.queryAll(Criterion.class);
	}

	@Override
	public Criterion create(Criterion data) {
		// TODO Auto-generated method stub
		return repo.save(data);
	}

	@Override
	public Criterion getByID(int id) {
		// TODO Auto-generated method stub
		return repo.getByID(Criterion.class, id);
	}

	@Override
	public Criterion update(Criterion data) {
		// TODO Auto-generated method stub
		return repo.update(data);
	}

	@Override
	public Criterion getByNo(String no) {
		List<Criterion> rs = (List<Criterion>) repo.findByCriteria(Criterion.class, Restrictions.eq("criterionNo", no));
		if(rs.size()>0)return rs.get(0);
		return new Criterion();
	}

}
