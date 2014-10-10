package wh.tb.crxyspider.selector;

/**
 * Object contains regex results.<br>
 * For multi group result extension.<br>
 *
 * @author 280320889@qq.com <br>
 * @since 1.0
 */
class RegexResult {

    private String[] groups;

    public static final wh.tb.crxyspider.selector.RegexResult EMPTY_RESULT = new wh.tb.crxyspider.selector.RegexResult();

    public RegexResult() {

    }

    public RegexResult(String[] groups) {
        this.groups = groups;
    }

    public String get(int groupId) {
        if (groups == null) {
            return null;
        }
        return groups[groupId];
    }

}
