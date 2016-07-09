package mx.infotec.dads.insight.pdes.model;

import java.util.Random;

import org.testng.annotations.DataProvider;

public class TableDataProvider {

	@DataProvider(name = "getDouble")
	public static Object[][] getDouble() {
		return new Object[][] { { genRandomDouble(), genRandomDouble() },
				{ genRandomDouble(), genRandomDouble() } };
	}

	private static double genRandomDouble() {
		Random r = new Random();
		double rangeMin = 1;
		double rangeMax = 101;
		return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	}
}