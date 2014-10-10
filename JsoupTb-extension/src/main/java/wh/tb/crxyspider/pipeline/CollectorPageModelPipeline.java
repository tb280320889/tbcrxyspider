package wh.tb.crxyspider.pipeline;

import wh.tb.crxyspider.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 280320889@qq.com
 */
public class CollectorPageModelPipeline<T> implements PageModelPipeline<T> {

    private List<T> collected = new ArrayList<T>();

    @Override
    public synchronized void process(T t, Task task) {
        collected.add(t);
    }

    public List<T> getCollected() {
        return collected;
    }
}
