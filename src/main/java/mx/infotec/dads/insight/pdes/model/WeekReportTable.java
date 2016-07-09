package mx.infotec.dads.insight.pdes.model;

import java.io.IOException;
import java.io.Serializable;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import mx.infotec.dads.insight.main.DateCalculator;

/**
 * Clase que representa la tabla de reporte semanal
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class WeekReportTable implements Serializable {

    private static final long serialVersionUID = 8925317157999586338L;
    private TableWrapper tableWrapper;

    public WeekReportTable(Document doc) throws IOException {
	Elements elements = doc.select("body table tbody tr");
	this.tableWrapper = readValues(elements);
    }

    public double getDoubleProperty(int i, int j) {
	return tableWrapper.getDoubleValues()[i][j];
    }

    public String getStringProperty(int i, int j) {
	return tableWrapper.getStringValues()[i][j];
    }

    public int getNumRows() {
	return Row.values().length;
    }

    public int getNumCols() {
	return Column.values().length;
    }

    private static TableWrapper readValues(Elements elements) {

	double[][] dataTableValue = new double[Row.values().length][Column.values().length];
	String[][] dataTableStr = new String[Row.values().length][Column.values().length];
	int rowIndex = 0;

	for (Row row : Row.values()) {
	    int colIndex = 0;
	    for (Column col : Column.values()) {
		dataTableValue[rowIndex][colIndex] = getValue(row, col, elements);
		dataTableStr[rowIndex][colIndex] = getStringValue(row, col, elements);
		colIndex++;
	    }
	    rowIndex++;
	}
	return new TableWrapper(dataTableValue, dataTableStr);
    }

    private static double getValue(Row row, Column column, Elements table) {
	Elements elements = table.get(row.getIndex()).getAllElements();
	String sValue = elements.get(column.getIndex()).text();
	if (column == Column.HORAS_ACTUAL || column == Column.HORAS_PLAN) {
	    return DateCalculator.convertDate(sValue);
	} else if (column == Column.EV_ACTUAL || column == Column.EV_PLAN) {
	    return getDoubleValue(sValue.replaceFirst("%", ""));
	} else {
	    return getDoubleValue(sValue);
	}
    }

    private static String getStringValue(Row row, Column column, Elements table) {
	Elements elements = table.get(row.getIndex()).getAllElements();
	return stringSanitization(elements.get(column.getIndex()).text());
    }

    private static String stringSanitization(String cadVal) {
	int nbsp = -1;
	if (!cadVal.isEmpty()) {
	    nbsp = cadVal.charAt(0);
	}

	if ("".equals(cadVal) || cadVal.isEmpty() || nbsp == 160) {
	    return "";
	} else {
	    return cadVal;
	}
    }

    private static double getDoubleValue(String cad) {
	String cadVal = cad.replaceAll("\\s", "");
	int nbsp = -1;
	if (!cadVal.isEmpty()) {
	    nbsp = cadVal.charAt(0);
	}

	if ("".equals(cadVal) || cadVal.isEmpty() || nbsp == 160) {
	    return 0.0;
	}
	return Double.parseDouble(cadVal);
    }
}
