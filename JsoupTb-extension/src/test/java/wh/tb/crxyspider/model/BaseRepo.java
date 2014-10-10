package wh.tb.crxyspider.model;

import wh.tb.crxyspider.model.annotation.ExtractBy;

/**
 * @author 280320889@qq.com
 */
public class BaseRepo {

    @ExtractBy("//ul[@class='pagehead-actions']/li[1]//a[@class='social-count js-social-count']/text()")
    protected int star;
}
