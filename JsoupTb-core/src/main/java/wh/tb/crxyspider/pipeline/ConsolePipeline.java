package wh.tb.crxyspider.pipeline;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;

import java.util.Map;

/**
 * IntelliJ IDEA
 *
 * Write results in console.<br>
 * Usually used in test.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public class ConsolePipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		System.out.println("get page: " + resultItems.getRequest().getUrl());
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			System.out.println(entry.getKey() + ":\t" + entry.getValue());
		}
	}
}
