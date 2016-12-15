package cn.edu.pku.hql.bitcoin;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by huangql on 12/15/16.
 */
public class Document {
    private String version;
    private String url;
    private String date;
    private String ip;
    private String refere;
    private String anchorText;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Document(String version, String url, String date, String ip, String refere, String anchorText) {
        this.version = version;
        this.url = url;
        this.date = date;
        this.ip = ip;
        this.refere = refere;
        this.anchorText = anchorText;
    }

//    public static Document constructDocument(BufferedReader reader) throws IOException {
//
//    }
}
