package wh.tb.crxyspider.handler;

import wh.tb.crxyspider.Request;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public interface RequestMatcher {

    /**
     * Check whether to process the page.<br></br>
     * Please DO NOT change page status in this method.
     *
     * @param page
     *
     * @return
     */
    public boolean match(Request page);

    public enum MatchOther {
        YES, NO
    }
}
