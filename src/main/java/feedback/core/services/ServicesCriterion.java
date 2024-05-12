package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Criterion;
/**
 * Created by Nhat. <br/>
 * value=servicesCriterion
 */
public interface ServicesCriterion {
public Criterion getCriterionActive();
public List<Criterion> getAll();
public Criterion create(Criterion data);
public Criterion getByID(int id);
public Criterion update(Criterion data);
public Criterion getByNo(String no);
}
