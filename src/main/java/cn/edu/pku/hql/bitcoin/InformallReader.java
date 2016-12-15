package cn.edu.pku.hql.bitcoin;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Created by huangql on 12/15/16.
 */
public class InformallReader {

    private static String getValue(String line) {
        return line.substring(line.indexOf(':') + 2);
    }

    private static String readLine(InputStream in) throws IOException {
        StringBuffer sb = new StringBuffer();
        int b;
        while ((b = in.read()) != -1) {
            char value = (char) b;
            if (value == '\n')  break;
            sb.append(value);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, DataFormatException {
        if (args.length < 1) {
            System.err.println("Args: fileName");
            System.exit(1);
        }
        FileInputStream in = new FileInputStream(args[0]);
        //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        int docCnt = 0;
        int unzipLength, length;
        //while ((line = reader.readLine()) != null) {
        line = readLine(in);
        String version = getValue(line);    line = readLine(in);
        String url = getValue(line);    line = readLine(in);
        String date = getValue(line);   line = readLine(in);
        String ip = getValue(line); line = readLine(in);
        String referer = getValue(line); line = readLine(in);
        String anchorText = getValue(line); line = readLine(in);
        unzipLength = Integer.parseInt(getValue(line)); line = readLine(in);
        length = Integer.parseInt(getValue(line));
        System.out.println(length);
        line = readLine(in);

        byte[] buffer = new byte[length];
        System.out.println(in.read(buffer));
        Inflater decompresser = new Inflater();
        decompresser.setInput(buffer);
        byte[] result = new byte[unzipLength];
        int resultLength = decompresser.inflate(result);
        decompresser.end();
        System.out.println(resultLength);
        //System.out.println(new String(result, "GBK"));

        System.out.println(readLine(in));
        System.out.println(readLine(in));
//        StringBuffer sb = new StringBuffer();
//        line = "";
//        while (!line.startsWith("version")) {
//            sb.append(line + "\n\r");
//            line = readLine(in);
//        }
//        System.out.println(sb.length());
//        System.out.println(line);
//        System.out.println(reader.readLine());
//            docCnt++;
        //}
    }
}
