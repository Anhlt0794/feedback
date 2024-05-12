package feedback.core.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Pages implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;
	@Column(unique =true,nullable = false)
	private String no;
	private String title;
	private String icon;
	private String type;
	private String uri;
	private String location;
	@ManyToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY  )
	@JoinColumn(name="frid")
	private Pages pageFr;
	@OneToMany(mappedBy="pageFr")
	private List<Pages> pagechildren;

	public Pages() {
	}

	public Pages(String no, String title, String icon, String type, String uri,
			String location, List<Pages> pagechildren) {
		super();
		this.no = no;
		this.title = title;
		this.icon = icon;
		this.type = type;
		this.uri = uri;
		this.location = location;
		this.pagechildren = pagechildren;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Pages> getPagechildren() {
		return pagechildren;
	}

	public void setPagechildren(List<Pages> pagechildren) {
		this.pagechildren = pagechildren;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected String getLocation() {
		return location;
	}

	protected void setLocation(String location) {
		this.location = location;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Pages getPageFr() {
		return pageFr;
	}

	public void setPageFr(Pages pageFr) {
		this.pageFr = pageFr;
	}

	@Override
	public String toString() {
		return "Pages [pid=" + pid + ", no=" + no + ", title=" + title
				+ ", icon=" + icon + ", type=" + type + ", uri=" + uri
				+ ", location=" + location + ", pageFr=" + pageFr
				+ ", pagechildren=" + pagechildren + "]";
	}
	

}
