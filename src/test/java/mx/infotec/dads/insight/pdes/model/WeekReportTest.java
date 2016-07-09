package mx.infotec.dads.insight.pdes.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.http.client.utils.URIUtils;
import org.testng.annotations.Test;

public class WeekReportTest {
  @Test
  public void test() throws IOException, URISyntaxException {
    // String URL
    // ="http://localhost:2468/dads_strategy2016//reports/week.class?tl=auto&labelFilterAuto=t&pathFilterAuto=t";
    Properties properties = readProperties();
    String project = properties.getProperty("project");
    String port = properties.getProperty("port");
    URI uri = URIUtils.createURI("http", "localhost", Integer.parseInt(port),
        project + "//reports/week.class", "tl=auto&labelFilterAuto=t&pathFilterAuto=t", null);

    // String URL = readFile("test.html", Charset.defaultCharset());
//    String report = new WeekReport().createReport(uri.toString());
  }

  private static String readFile(String resource, Charset encoding)
      throws IOException, URISyntaxException {
    byte[] encoded = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI()));
    return new String(encoded, encoding);
  }

  private static Properties readProperties() throws IOException {
    Properties mainProperties = new Properties();
    FileInputStream file;
    String path = "./pdes.properties";
    file = new FileInputStream(path);
    mainProperties.load(file);
    file.close();
    return mainProperties;
  }
}
