package cn.edu.pku.hql.bitcoin;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by huangql on 12/20/16.
 */
public class InfomallReaderTest {

    @Test
    public void ReaderTest() throws Exception {
        try (InputStream in = InfomallReaderTest.class.getClassLoader()
                .getResourceAsStream("Web_Raw.small")) {
            InformallReader reader = new InformallReader(in);

            Document doc = reader.nextDocument();
            Assert.assertNotNull(doc.getContent());
            Assert.assertEquals(102564, doc.getContent().getBytes(InformallReader.WEB_PAGES_ENCODING).length);

            doc = reader.nextDocument();
            Assert.assertNotNull(doc.getContent());
            Assert.assertEquals(103779, doc.getContent().getBytes(InformallReader.WEB_PAGES_ENCODING).length);

            doc = reader.nextDocument();
            Assert.assertNotNull(doc.getContent());
            Assert.assertEquals(19357, doc.getContent().getBytes(InformallReader.WEB_PAGES_ENCODING).length);

            doc = reader.nextDocument();
            Assert.assertNotNull(doc.getContent());
            Assert.assertEquals(18500, doc.getContent().getBytes(InformallReader.WEB_PAGES_ENCODING).length);
        }
    }
}
