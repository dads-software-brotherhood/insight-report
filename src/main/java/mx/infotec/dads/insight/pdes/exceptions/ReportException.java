package mx.infotec.dads.insight.pdes.exceptions;

/**
 * Exception lanzada cuando no se cumple con una regla de negocio
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ReportException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReportException() {
	super();
    }

    public ReportException(String s) {
	super(s);
    }

    public ReportException(String s, Throwable throwable) {
	super(s, throwable);
    }

    public ReportException(Throwable throwable) {
	super(throwable);
    }

}
