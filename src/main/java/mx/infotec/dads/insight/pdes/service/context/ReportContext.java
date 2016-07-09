package mx.infotec.dads.insight.pdes.service.context;

import java.io.File;
import java.util.Date;

import mx.infotec.dads.insight.pdes.model.Report;
import mx.infotec.dads.insight.util.Constants;
import mx.infotec.dads.insight.util.DateUtils;
import mx.infotec.dads.insight.util.UrlPd;

/**
 * The ReportContext Class, is used throught the application to share the same
 * context.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ReportContext {
    private Report report;
    private UrlPd urlPd;
    private File outputFile;

    public ReportContext() {
	File newOutputFile = new File(Constants.REPORT_FOLDER + DateUtils.getDateFolderForma(new Date()));
	newOutputFile.mkdirs();
	this.outputFile = newOutputFile;
    }

    public Report getReport() {
	return report;
    }

    public void setReport(Report report) {
	this.report = report;
    }

    public UrlPd getUrlPd() {
	return urlPd;
    }

    public void setUrlPd(UrlPd urlPd) {
	this.urlPd = urlPd;
    }

    public File getOutputFile() {
	return outputFile;
    }
}
