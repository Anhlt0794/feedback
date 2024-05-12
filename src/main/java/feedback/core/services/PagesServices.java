package feedback.core.services;

import java.util.List;

import feedback.core.models.entities.Pages;

/**MangaerPropeties <br/>
 * Value="pageServices"
 * */
public interface PagesServices {
	public Pages getPage(String id);
	public List<Pages> getAllRootPages();
	public List<Pages> getAllPages();
	public Pages getPage(int id);
}
