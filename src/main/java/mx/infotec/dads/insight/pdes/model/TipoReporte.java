package mx.infotec.dads.insight.pdes.model;

/**
 * Clase que encapsula los tipos de reportes que se pueden llevar a cabo.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TipoReporte {
    private int id;
    private String desc;

    public TipoReporte(int id, String desc) {
	this.id = id;
	this.desc = desc;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

    @Override
    public String toString() {
	return desc;
    }
}
