package wh.tb.crxyspider.downloader;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wh.tb.crxyspider.*;
import wh.tb.crxyspider.pipeline.Pipeline;
import wh.tb.crxyspider.processor.PageProcessor;
import wh.tb.crxyspider.processor.SimplePageProcessor;
import wh.tb.crxyspider.selector.Html;
import wh.tb.crxyspider.selector.PlainText;
import wh.tb.crxyspider.utils.Experimental;
import wh.tb.crxyspider.utils.FilePersistentBase;
import wh.tb.crxyspider.utils.UrlUtils;

import java.io.*;

/**
 * Download file and saved to file for cache.<br>
 *
 * @author 280320889@qq.com
 * @since 1.0
 */
@Experimental
public class FileCache extends FilePersistentBase implements Downloader, Pipeline, PageProcessor {

    private Downloader downloaderWhenFileMiss;

    private final PageProcessor pageProcessor;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public FileCache(String startUrl, String urlPattern) {
        this(startUrl, urlPattern, "/data/wh.tb.crxyspider/temp/");
    }

    public FileCache(String startUrl, String urlPattern, String path) {
        this.pageProcessor = new SimplePageProcessor(startUrl, urlPattern);
        setPath(path);
        downloaderWhenFileMiss = new HttpClientDownloader();
    }

    public wh.tb.crxyspider.downloader.FileCache setDownloaderWhenFileMiss(Downloader downloaderWhenFileMiss) {
        this.downloaderWhenFileMiss = downloaderWhenFileMiss;
        return this;
    }

    @Override
    public Page download(Request request, Task task) {
        String path = this.path + "/" + task.getUUID() + "/";
        Page page = null;
        try {
            final File file = getFile(path + DigestUtils.md5Hex(request.getUrl()));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (line.equals("url:\t" + request.getUrl())) {
                final String html = getHtml(bufferedReader);
                page = new Page();
                page.setRequest(request);
                page.setUrl(PlainText.create(request.getUrl()));
                page.setHtml(Html.create(UrlUtils.fixAllRelativeHrefs(html, request.getUrl())));
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                logger.info("File not exist for url " + request.getUrl());
            } else {
                logger.warn("File read error for url " + request.getUrl(), e);
            }
        }
        if (page == null) {
            page = downloadWhenMiss(request, task);
        }
        return page;
    }

    @Override
    public void setThread(int thread) {

    }

    private String getHtml(BufferedReader bufferedReader) throws IOException {
        String line;
        StringBuilder htmlBuilder = new StringBuilder();
        line = bufferedReader.readLine();
        line = StringUtils.removeStart(line, "html:\t");
        htmlBuilder.append(line);
        while ((line = bufferedReader.readLine()) != null) {
            htmlBuilder.append(line);
        }
        return htmlBuilder.toString();
    }

    private Page downloadWhenMiss(Request request, Task task) {
        Page page = null;
        if (downloaderWhenFileMiss != null) {
            page = downloaderWhenFileMiss.download(request, task);
        }
        return page;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html")));
            printWriter.println("url:\t" + resultItems.getRequest().getUrl());
            printWriter.println("html:\t" + resultItems.get("html"));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }

    @Override
    public void process(Page page) {
          pageProcessor.process(page);
    }

    @Override
    public Site getSite() {
        return pageProcessor.getSite();
    }
}
