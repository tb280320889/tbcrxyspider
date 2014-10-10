package wh.tb.crxyspider.pipeline;

import wh.tb.crxyspider.ResultItems;
import wh.tb.crxyspider.Task;

/**
 * IntelliJ IDEA
 *
 * Pipeline is the persistent and offline process part of crawler.<br>
 * The interface Pipeline can be implemented to customize ways of persistent.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 * @see ConsolePipeline
 * @see FilePipeline
 */

public interface Pipeline {

	/**
	 * Process extracted results.
	 *
	 * @param resultItems
	 * @param task
	 */
	public void process(ResultItems resultItems, Task task);
}
