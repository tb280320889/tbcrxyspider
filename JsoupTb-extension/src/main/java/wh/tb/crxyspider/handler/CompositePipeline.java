package wh.tb.crxyspider.handler;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;
import wh.tb.crxyspider.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 280320889qq.com
 */
public class CompositePipeline implements Pipeline {

    private List<wh.tb.crxyspider.handler.SubPipeline> subPipelines = new ArrayList<wh.tb.crxyspider.handler.SubPipeline>();

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (wh.tb.crxyspider.handler.SubPipeline subPipeline : subPipelines) {
            if (subPipeline.match(resultItems.getRequest())) {
                RequestMatcher.MatchOther matchOtherProcessorProcessor = subPipeline.processResult(resultItems, task);
                if (matchOtherProcessorProcessor == null || matchOtherProcessorProcessor != RequestMatcher.MatchOther.YES) {
                    return;
                }
            }
        }
    }

    public wh.tb.crxyspider.handler.CompositePipeline addSubPipeline(wh.tb.crxyspider.handler.SubPipeline subPipeline) {
        this.subPipelines.add(subPipeline);
        return this;
    }

    public wh.tb.crxyspider.handler.CompositePipeline setSubPipeline(wh.tb.crxyspider.handler.SubPipeline... subPipelines) {
        this.subPipelines = new ArrayList<wh.tb.crxyspider.handler.SubPipeline>();
        for (SubPipeline subPipeline : subPipelines) {
            this.subPipelines.add(subPipeline);
        }
        return this;
    }

}
