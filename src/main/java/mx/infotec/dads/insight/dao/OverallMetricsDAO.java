package mx.infotec.dads.insight.dao;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import mx.infotec.dads.insight.pdes.exceptions.ReportException;
import mx.infotec.dads.insight.pdes.model.SizeReportTable;
import mx.infotec.dads.insight.util.UrlPd;

/**
 * ReportDAO, xpath query to the PDES Report
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class OverallMetricsDAO {

    private Document overallMetrics;

    public OverallMetricsDAO(UrlPd urlPd) throws ReportException {
	try {
	    this.overallMetrics = Jsoup.connect(urlPd.getReportsPlanSummaryUrl().toString())
		    .header("Referer", urlPd.getOveralMetricsUrl().toString()).get();
	} catch (NumberFormatException | IOException e) {
	    throw new ReportException("OverallMetricsDAO:", e);
	}
    }

    public SizeReportTable findSizeTable(String xpathQuery) {
	Elements overallMetricsElements = overallMetrics.select(xpathQuery);
	Element sizeTable = overallMetricsElements.get(1);
	sizeTable.addClass("table table-bordered table-striped table-responsive");
	return new SizeReportTable(sizeTable.toString());
    }
}
