package wh.tb.crxyspider;

/**
 * IntelliJ IDEA
 *
 * Listener of Spider on page processing. Used for monitor and such on.
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */

public interface SpiderListener {

	public void onSuccess(Request request);

	public void onError(Request request);
}
