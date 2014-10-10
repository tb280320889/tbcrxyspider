package wh.tb.crxyspider.downloader;

import org.junit.Ignore;
import org.junit.Test;
import wh.tb.crxyspider.Spider;

/**
 * @author 280320889@qq.com <br>
 */
public class FileCacheTest {

    @Ignore("takes long")
    @Test
    public void test() {
        wh.tb.crxyspider.downloader.FileCache fileCache = new FileCache("http://my.oschina.net/flashsword/blog", "http://my.oschina.net/flashsword/blog/*");
        Spider.create(fileCache).downloader(fileCache).pipeline(fileCache).run();
    }
}
