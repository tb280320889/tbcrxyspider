package wh.tb.crxyspider.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.pipeline.PageModelPipeline;

/**
 * Print page model in console.<br>
 * Usually used in test.<br>
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class ConsolePageModelPipeline implements PageModelPipeline {
    @Override
    public void process(Object o, Task task) {
        System.out.println(ToStringBuilder.reflectionToString(o));
    }
}
