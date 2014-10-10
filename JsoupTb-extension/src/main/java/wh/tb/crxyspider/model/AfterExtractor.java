package wh.tb.crxyspider.model;

import wh.tb.crxyspider.Page;

/**
 * Interface to be implemented by page models that need to do something after fields are extracted.<br>
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public interface AfterExtractor {

    public void afterProcess(Page page);
}
