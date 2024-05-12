package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Feedback;
import feedback.core.models.entities.Result;

/**
 * Created by Nhat. <br/>
 * value=resultServies
 */
public interface ResultServies {
	public Result update(Result rs);
	public Result save(Result rs);
	public List<Result> loadResult();
	public List<Feedback> loadFeedBack();
}
