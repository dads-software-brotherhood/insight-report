package mx.infotec.dads.insight.pdes.exceptions;

/**
 * Exception lanzada cuando no se cumple con una regla de negocio
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ReportRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReportRuntimeException() {
	super();
    }

    public ReportRuntimeException(String s) {
	super(s);
    }

    public ReportRuntimeException(String s, Throwable throwable) {
	super(s, throwable);
    }

    public ReportRuntimeException(Throwable throwable) {
	super(throwable);
    }

}
