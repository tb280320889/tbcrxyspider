package wh.tb.crxyspider.scheduler.component;

import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Task;

/**
 * IntelliJ IDEA
 *
 * Remove duplicate requests.
 *
 * @author 280320889@qq.com
 * @since 1.0
 */
public interface DuplicateRemover {
	/**
	 *
	 * Check whether the request is duplicate.
	 *
	 * @param request
	 * @param task
	 * @return
	 */
	public boolean isDuplicate(Request request, Task task);

	/**
	 * Reset duplicate check.
	 * @param task
	 */
	public void resetDuplicateCheck(Task task);

	/**
	 * Get TotalRequestsCount for monitor.
	 * @param task
	 * @return
	 */
	public int getTotalRequestsCount(Task task);

}
