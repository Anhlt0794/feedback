package feedback;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import feedback.core.models.entities.Feedback;
import feedback.core.models.entities.Question;
import feedback.core.models.entities.Result;
import feedback.core.services.EntityManagerServices;
import feedback.core.services.ServicesCriterion;
import feedback.core.services.StudentServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jpa/business-config.xml")
public class TestServices {
	@Autowired
	private EntityManagerServices enti;
	@Autowired
	private StudentServer studentServer;
	@Autowired
	private ServicesCriterion criterion;
	
	private  Result res = new Result();

	List<Feedback> lf = new ArrayList<Feedback>();
	List<Question> lq = new ArrayList<Question>();

	@Before
	@Transactional
	@Rollback(value=true)
	public void setup(){
		lq = (List<Question>) criterion.getCriterionActive().getQuestions();
		int i = 1;
		for(Question q : lq){
			Feedback f = new Feedback(3, i, q, res);
			lf.add(f);
			i++;
		}
		res.setStudent(studentServer.getAll().get(0));
		res.setFeedbacks(lf);
		res.setResultID(2L);
		
		res = enti.update(res);
	}
	@Test
	public void test(){
		Assert.assertEquals(res.getResultID(), 2);
		for(Feedback f: res.getFeedbacks()){
			System.out.println(f.getQuestion().getQuestionTitle());
		}
	}
	

}
