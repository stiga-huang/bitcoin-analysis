# bitcoin-analysis
Currently analyze web pages provided by [InfoMall](http://www.infomall.cn)


## InfoMall Data Reader

### Compile
```bash
git clone https://github.com/stiga-huang/bitcoin-analysis.git
cd bitcoin-analysis
mvn clean package
```
Then a jar file should be found in `target` directory.

### Print Web Pages
`InfomallReader` can run directly, given arguments of input file and an optional documents count (10 in default).
```bash
Args: inputFile [docs]
```
For example:
```bash
java -cp target/bitcoin-analysis-1.0-SNAPSHOT.jar cn.edu.pku.hql.bitcoin.InfomallReader src/test/resources/Web_Raw.small 2
```

### Use in your project
```java
import cn.edu.pku.hql.bitcoin.Document;
import cn.edu.pku.hql.bitcoin.InfomallReader;

// somewhere in your codes
try (InfomallReader reader = new InfomallReader(new FileInputStream(fileName))) {
  while (true) {
    Document doc = reader.nextDocument();
    if (doc == null)  break;
    // process the doc
  }
}
```
