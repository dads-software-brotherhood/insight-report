package mx.infotec.dads.insight.pdes.model;

/**
 * Clase que encapsula el reporte de tamaños.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class SizeReportTable {
    private String data;

    public SizeReportTable(String data) {
	this.data = data;
    }

    public String getData() {
	return this.data;
    }
}
