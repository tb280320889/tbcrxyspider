package wh.tb.crxyspider.handler;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.handler.RequestMatcher;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public interface SubPipeline extends RequestMatcher {

    /**
     * process the page, extract urls to fetch, extract the data and store
     *
     * @param page
     * @param task
     * @return whether continue to match
     */
    public MatchOther processResult(ResultItems resultItems, Task task);

}
