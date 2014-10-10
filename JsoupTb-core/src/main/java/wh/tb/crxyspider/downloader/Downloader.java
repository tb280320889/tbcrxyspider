package wh.tb.crxyspider.downloader;

import wh.tb.crxyspider.Page;
import wh.tb.crxyspider.Request;
import wh.tb.crxyspider.Task;

/**
 * IntelliJ IDEA
 *
 * Downloader is the part that downloads web pages and store in Page object. <br>
 * Downloader has {@link #setThread(int)} method because downloader is always the bottleneck of a crawler,
 * there are always some mechanisms such as pooling in downloader, and pool size is related to thread numbers.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */

public interface Downloader {

	/**
	 * Downloads web pages and store in Page object.
	 *
	 * @param request
	 * @param task
	 * @return page
	 */
	public Page download(Request request, Task task);

	/**
	 * Tell the downloader how many threads the spider used.
	 * @param threadNum number of threads
	 */
	public void setThread(int threadNum);
}
