package feedback.web.bean.teacher;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@SessionScoped
public class ChartBean {
	private PieChartModel pieModel1;
	// @ManagedProperty("#{loginTeacherBean}")
	// private LoginTeacherBean loginbean;
	private boolean choosetable = false;
	private boolean choosechart = false;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	public void chooseTable() {
		setChoosetable(true);
		setChoosechart(false);
	}
	public void chooseChart() {
		setChoosechart(true);
		setChoosetable(false);
	}

	private void createPieModels() {
		pieModel1 = new PieChartModel();

		pieModel1.set("Mức 5", 540);
		pieModel1.set("Mức 4", 121);
		pieModel1.set("Mức 3", 100);
		pieModel1.set("Mức 2", 85);
		pieModel1.set("Mức 1", 47);

		pieModel1.setTitle("Kết quả đánh giá của sinh viên");
		pieModel1.setLegendPosition("w");
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public boolean isChoosechart() {
		return choosechart;
	}

	public void setChoosechart(boolean choosechart) {
		this.choosechart = choosechart;
	}

	public boolean isChoosetable() {
		return choosetable;
	}

	public void setChoosetable(boolean choosetable) {
		this.choosetable = choosetable;
	}

	// public LoginTeacherBean getLoginbean() {
	// return loginbean;
	// }
	//
	//
	// public void setLoginbean(LoginTeacherBean loginbean) {
	// this.loginbean = loginbean;
	// }

}
