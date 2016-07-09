package mx.infotec.dads.insight.main;

/**
 * DateCalculator utilizada para llevar a cabo las funciones de transformación
 * de fecha
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DateCalculator {

    private DateCalculator() {

    }

    public static double convertDate(String date) {
	String[] dateone = date.split(":");
	int hours = Integer.parseInt(dateone[0]);
	double minutes = Double.parseDouble(dateone[1]) / 60;
	return hours + minutes;
    }
}
