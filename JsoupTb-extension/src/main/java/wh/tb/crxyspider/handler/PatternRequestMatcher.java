package wh.tb.crxyspider.handler;

import wh.tb.crxyspider.Request;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * <p></p>
 * A PatternHandler is in charge of both page extraction and data processing by implementing
 * its two abstract methods.
 */
public abstract class PatternRequestMatcher implements RequestMatcher {

    /**
     * match pattern. only matched page should be handled.
     */
    protected String pattern;

    private Pattern patternCompiled;

    /**
     * @param pattern url pattern to handle
     */
    public PatternRequestMatcher(String pattern) {
        this.pattern = pattern;
        this.patternCompiled = Pattern.compile(pattern);
    }

    @Override
    public boolean match(Request request) {
        return patternCompiled.matcher(request.getUrl()).matches();
    }
}
