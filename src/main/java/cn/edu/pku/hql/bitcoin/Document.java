package cn.edu.pku.hql.bitcoin;


/**
 * Created by huangql on 12/15/16.
 */
public class Document {
    private String version;
    private String url;
    private String date;
    private String ip;
    private String referer;
    private String anchorText;
    private String content;

    public Document(String version, String url, String date, String ip, String referer, String anchorText) {
        this.version = version;
        this.url = url;
        this.date = date;
        this.ip = ip;
        this.referer = referer;
        this.anchorText = anchorText;
    }

    public String getVersion() {
        return version;
    }

    public String getContent() {
        return content;
    }

    public Document setContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getIp() {
        return ip;
    }

    public String getReferer() {
        return referer;
    }

    public String getAnchorText() {
        return anchorText;
    }

    public String toString() {
        return "version: " + version + "\nurl: " + url + "\ndate: " + date +
                "\nip: " + ip + "\nreferer: " + referer + "\nanchortext: " +
                anchorText + "\n\n" + content;
    }
}
