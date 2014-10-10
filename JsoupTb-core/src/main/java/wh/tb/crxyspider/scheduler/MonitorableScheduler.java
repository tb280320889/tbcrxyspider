package wh.tb.crxyspider.scheduler;

import wh.tb.crxyspider.Task;

/**
 * IntelliJ IDEA
 *
 * The scheduler whose requests can be counted for monitor.
 *
 * @author 280320889@qq.com
 * @since 1.0
 */
public interface MonitorableScheduler extends Scheduler {

	public int getLeftRequestsCount(Task task);

	public int getTotalRequestsCount(Task task);

}
