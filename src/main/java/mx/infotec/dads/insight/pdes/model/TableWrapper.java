package mx.infotec.dads.insight.pdes.model;

import java.io.Serializable;

/**
 * Clase intermedia que lee el contenido en bruto de las tablas del reporte. Se
 * necesita procesar antes de convertir al reporte real
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TableWrapper implements Serializable {
    private static final long serialVersionUID = 4533821364231259733L;
    private double[][] doubleValues;
    private String[][] stringValues;

    public TableWrapper(double[][] doubleValues, String[][] stringValues) {
	this.doubleValues = doubleValues;
	this.stringValues = stringValues;
    }

    public String[][] getStringValues() {
	return stringValues;
    }

    public double[][] getDoubleValues() {
	return doubleValues;
    }
}
