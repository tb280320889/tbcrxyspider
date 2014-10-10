package wh.tb.crxyspider.model;

import wh.tb.crxyspider.utils.Experimental;

/**
 * Interface to be implemented by page mode.<br>
 * Can be used to identify a page model, or be used as name of file storing the object.<br>
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
@Experimental
public interface HasKey {

    /**
     *
     *
     * @return key
     */
    public String key();
}
