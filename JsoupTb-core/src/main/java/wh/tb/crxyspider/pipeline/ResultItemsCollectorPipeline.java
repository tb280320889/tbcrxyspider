package wh.tb.crxyspider.pipeline;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * IntelliJ IDEA
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */

public class ResultItemsCollectorPipeline implements CollectorPipeline<ResultItems> {

	private List<ResultItems> collector = new ArrayList<>();

	@Override
	public synchronized void process(ResultItems resultItems, Task task) {
		collector.add(resultItems);
	}

	@Override
	public List<ResultItems> getCollected() {
		return collector;
	}
}