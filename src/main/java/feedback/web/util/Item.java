package feedback.web.util;

public class Item {
	private Object view;
	private Object select;
	public Item(Object view, Object select) {
		this.view = view;
		this.select = select;
	}
	public Object getView() {
		return view;
	}
	public void setView(Object view) {
		this.view = view;
	}
	public Object getSelect() {
		return select;
	}
	public void setSelect(Object select) {
		this.select = select;
	}
	
	
}
