package mx.infotec.dads.insight.pdes.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.annotations.Test;

import mx.infotec.dads.insight.pdes.service.WeekReportService;

public class PerformanceTableTest {
	@Test
	public void test() throws IOException, URISyntaxException {
		WeekReportTable table = deSerializeWeekTable();
//		serializeWeekTable(table);
		PerformanceReportTable pTable = WeekReportService.computeData(table);
		System.out.println(pTable.toString());
	}

	public static void main(String[] args) {

	}

	public static WeekReportTable deSerializeWeekTable() throws URISyntaxException {
		URL resource = PerformanceTableTest.class.getClassLoader().getResource("testTable.ser");
		File file = new File(resource.toURI());
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			WeekReportTable p = (WeekReportTable) in.readObject();
			in.close();
			return p;
		} catch (Exception ex) {
			return null;
		}
	}

	public static void serializeWeekTable(WeekReportTable table) throws IOException {
		FileOutputStream fileOut = new FileOutputStream("testTable.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(table);
		out.close();
		fileOut.close();
	}
}
