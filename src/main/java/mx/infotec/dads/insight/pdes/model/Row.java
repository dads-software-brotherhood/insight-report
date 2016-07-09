package mx.infotec.dads.insight.pdes.model;

/**
 * Clase que encapsula un renglon en el reporte semanal
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public enum Row {
    SEMANA_PASADA(2), A_LA_FECHA(3), PROMEDIO_SEMANA(4), TAREAS_COMPLETADAS(5);
    private int index;

    private Row(int index) {
	this.index = index;
    }

    public int getIndex() {
	return index;
    }
}
