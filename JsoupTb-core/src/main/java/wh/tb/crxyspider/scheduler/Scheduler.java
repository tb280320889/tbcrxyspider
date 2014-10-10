package wh.tb.crxyspider.scheduler;

import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Task;

/**
 * IntelliJ IDEA
 *
 * Scheduler is the part of url management.<br>
 * You can implement interface Scheduler to do:
 * manage urls to fetch
 * remove duplicate urls
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
public interface Scheduler {

	/**
	 * add a url to fetch
	 *
	 * @param request
	 * @param task
	 */
	public void push(Request request, Task task);

	/**
	 * get an url to crawl
	 *
	 * @param task the task of spider
	 * @return the url to crawl
	 */
	public Request poll(Task task);

}