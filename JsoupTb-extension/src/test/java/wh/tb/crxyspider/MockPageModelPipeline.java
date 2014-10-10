package wh.tb.crxyspider;

import junit.framework.Assert;
import wh.tb.crxyspider.pipeline.PageModelPipeline;

/**
 * @author 280320889@qq.com
 */
public class MockPageModelPipeline implements PageModelPipeline {
    @Override
    public void process(Object o, Task task) {
        Assert.assertNotNull(o);
    }
}
