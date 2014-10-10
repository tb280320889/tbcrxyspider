package wh.tb.crxyspider.pipeline;

import wh.tb.crxyspider.Task;

/**
 * Implements PageModelPipeline to persistent your page model.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public interface PageModelPipeline<T> {

    public void process(T t, Task task);

}
