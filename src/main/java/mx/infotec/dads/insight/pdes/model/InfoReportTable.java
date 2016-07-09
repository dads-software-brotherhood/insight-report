package mx.infotec.dads.insight.pdes.model;

import java.util.Date;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.insight.util.DateUtils;

/**
 * Clase que encapsula la tabla de reportes.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class InfoReportTable {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoReportTable.class);

    private String dateReport;
    private String reportedPeriod;
    private String dateEndPlanned;
    private String status;
    private String dateForecast;

    public InfoReportTable(Document doc) {
	Elements elements = doc.select("body table[name=STATS]");
	extractInfo(elements);
    }

    private void extractInfo(Elements elements) {
	this.dateReport = DateUtils.convertDateToString(new Date());
	Element dateEndPlannedElement = elements.get(0).getAllElements().get(5);
	this.setDateEndPlanned(dateEndPlannedElement.text());
	this.setDateForecast(readForeCast(elements));
    }

    private static String readForeCast(Elements elements) {
	try {
	    Elements allElements = elements.get(0).getAllElements();
	    if (allElements.size() >= 278) {
		Element dateForecast = elements.get(0).getAllElements().get(278);
		return dateForecast.text();
	    } else {
		return "";
	    }
	} catch (Exception e) {
	    LOGGER.info("readForeCast", e);
	}
	return "No hay valores para estimar";
    }

    public String getDateReport() {
	return dateReport;
    }

    public void setDateReport(String dateReport) {
	this.dateReport = dateReport;
    }

    public String getReportedPeriod() {
	return reportedPeriod;
    }

    public void setReportedPeriod(String reportedPeriod) {
	this.reportedPeriod = reportedPeriod;
    }

    public String getDateEndPlanned() {
	return dateEndPlanned;
    }

    public void setDateEndPlanned(String dateEndPlanned) {
	this.dateEndPlanned = dateEndPlanned;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getDateForecast() {
	return dateForecast;
    }

    public void setDateForecast(String dateForecast) {
	this.dateForecast = dateForecast;
    }

}
