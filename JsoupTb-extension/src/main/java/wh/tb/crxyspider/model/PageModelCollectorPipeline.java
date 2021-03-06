package wh.tb.crxyspider.model;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.model.annotation.ExtractBy;
import wh.tb.crxyspider.pipeline.CollectorPageModelPipeline;
import wh.tb.crxyspider.pipeline.CollectorPipeline;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
class PageModelCollectorPipeline<T> implements CollectorPipeline<T> {

    private final CollectorPageModelPipeline<T> classPipeline = new CollectorPageModelPipeline<T>();

    private final Class<?> clazz;

    PageModelCollectorPipeline(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> getCollected() {
        return classPipeline.getCollected();
    }

    @Override
    public synchronized void process(ResultItems resultItems, Task task) {
        Object o = resultItems.get(clazz.getCanonicalName());
        if (o != null) {
            Annotation annotation = clazz.getAnnotation(ExtractBy.class);
            if (annotation == null || !((ExtractBy) annotation).multi()) {
                classPipeline.process((T) o, task);
            } else {
                List<Object> list = (List<Object>) o;
                for (Object o1 : list) {
                   classPipeline.process((T) o1, task);
                }
            }
        }
    }
}
