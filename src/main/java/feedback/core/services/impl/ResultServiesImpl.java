package feedback.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feedback.core.models.entities.Feedback;
import feedback.core.models.entities.Result;
import feedback.core.repositories.EntityChangeRepo;
import feedback.core.services.ResultServies;

@Service("resultServies")
public class ResultServiesImpl implements ResultServies {
	@Autowired
	private EntityChangeRepo repo;

	@Override
	public Result update(Result rs) {
		// TODO Auto-generated method stub
		return repo.update(rs);
	}

	public EntityChangeRepo getRepo() {
		return repo;
	}

	public void setRepo(EntityChangeRepo repo) {
		this.repo = repo;
	}

	@Override
	public Result save(Result rs) {
		// TODO Auto-generated method stub
		return repo.save(rs);
	}

	@Override
	public List<Result> loadResult() {
		// TODO Auto-generated method stub
		List<Result> listResult =(List<Result>) repo.queryAll(Result.class);
		return listResult;
	}

	@Override
	public List<Feedback> loadFeedBack() {
		// TODO Auto-generated method stub
		List<Feedback> listFeedback = (List<Feedback>) repo.queryAll(Feedback.class);
		return listFeedback;
	}

}
