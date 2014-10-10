package wh.tb.crxyspider.model;

import wh.tb.crxyspider.model.annotation.HelpUrl;
import wh.tb.crxyspider.model.annotation.TargetUrl;

/**
 * @author 280320889@qq.com
 */
@TargetUrl(value = "http://wh.tb.crxyspider.io/post/\\d+",sourceRegion = "//li[@class='post']")
@HelpUrl(value = "http://wh.tb.crxyspider.io/list/\\d+",sourceRegion = "//li[@class='list']")
public class MockModel {

}
