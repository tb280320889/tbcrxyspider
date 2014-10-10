package wh.tb.crxyspider;

/**
 * IntelliJ IDEA
 *
 * Interface for identifying different tasks.<br>
 * @author 280320889@qq.com <br>
 * @since 1.0
 * @see wh.tb.crxyspider.scheduler.Scheduler
 * @see wh.tb.crxyspider.pipeline.Pipeline
 */

public interface Task {

	/**
	 * unique id for a task.
	 *
	 * @return uuid
	 */
	public String getUUID();

	/**
	 * site of a task
	 *
	 * @return site
	 */
	public Site getSite();

}
