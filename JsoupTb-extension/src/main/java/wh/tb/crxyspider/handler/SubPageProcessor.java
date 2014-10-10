package wh.tb.crxyspider.handler;

import wh.tb.crxyspider.Page;

/**
 * @author 280320889@qq.com
 */
public interface SubPageProcessor extends RequestMatcher {

	/**
	 * process the page, extract urls to fetch, extract the data and store
	 *
	 * @param page
	 *
	 * @return whether continue to match
	 */
	public MatchOther processPage(Page page);

}
