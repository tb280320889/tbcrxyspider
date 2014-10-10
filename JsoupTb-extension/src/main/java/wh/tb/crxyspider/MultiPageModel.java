package wh.tb.crxyspider;

import wh.tb.crxyspider.utils.Experimental;

import java.util.Collection;

/**
 * Extract an object of more than one pages, such as news and articles.<br>
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
@Experimental
public interface MultiPageModel {

    /**
     * Page key is the identifier for the object.
     *
     * @return page key
     */
    public String getPageKey();

    /**
     * page is the identifier of a page in pages for one object.
     *
     * @return page
     */
    public String getPage();

    /**
     * other pages to be extracted.<br>
     * It is used to judge whether an object contains more than one page, and whether the pages of the object are all extracted.
     *
     * @return other pages
     */
    public Collection<String> getOtherPages();

    /**
     * Combine multiPageModels to a whole object.
     *
     * @param multiPageModel
     * @return multiPageModel combined
     */
    public wh.tb.crxyspider.MultiPageModel combine(wh.tb.crxyspider.MultiPageModel multiPageModel);

}
