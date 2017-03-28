package cn.edu.pku.hql.bitcoin;

import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Created by huangql on 12/15/16.
 */
public class InfomallReader implements AutoCloseable {

    public static String WEB_PAGES_ENCODING = "GBK";

    private InputStream in;

    public InfomallReader(InputStream inputStream) {
        this.in = inputStream;
    }

    public void close() throws IOException {
        in.close();
    }

    public Document nextDocument() throws IOException, DataFormatException {
        String line;
        do {
            line = readLine();
            if (line == null)   return null;
        } while (line.isEmpty());
        if (!line.startsWith("version")) {
            throw new IOException("Infomall data format error: each document should start with version");
        }

        // read meta data
        String version = line.substring(line.indexOf(':') + 2);
        String url = getValue();
        String date = getValue();
        String ip = getValue();
        String referer = getValue();
        String anchorText = getValue();
        int unzipLength, length;
        unzipLength = Integer.parseInt(getValue());
        length = Integer.parseInt(getValue());
        readLine(); // meta data end with an empty line

        // decompress data
        byte[] buffer = new byte[length];
        in.read(buffer);
        Inflater decompresser = new Inflater();
        decompresser.setInput(buffer);
        byte[] result = new byte[unzipLength];
        decompresser.inflate(result);
        decompresser.end();

        return new Document(version, url, date, ip, referer, anchorText)
                .setContent(new String(result, WEB_PAGES_ENCODING));
    }

    private String getValue() throws IOException {
        String line = readLine();
        return line.substring(line.indexOf(':') + 2);
    }

    private String readLine() throws IOException {
        StringBuffer sb = new StringBuffer();
        int b;
        while ((b = in.read()) != -1) {
            char value = (char) b;
            if (value == '\n')  break;
            sb.append(value);
        }
        if (b == -1 && sb.length() == 0)
            return null;
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, DataFormatException {
        if (args.length < 1) {
            System.err.println("Args: file [docs]");
            System.exit(1);
        }
        String fileName = args[0];
        int docs = 10;
        if (args.length < 2) {
            System.out.println("Read 10 documents in default.");
        } else {
            docs = Integer.parseInt(args[1]);
        }
        try (InfomallReader reader = new InfomallReader(new FileInputStream(fileName))) {
            while (docs-- > 0) {
                Document doc = reader.nextDocument();
                if (doc == null) {
                    System.out.println("No more documents.");
                    break;
                }
                System.out.println(doc);
            }
        }
    }
}
