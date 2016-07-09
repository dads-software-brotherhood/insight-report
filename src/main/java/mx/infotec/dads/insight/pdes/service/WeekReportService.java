package mx.infotec.dads.insight.pdes.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import mx.infotec.dads.insight.dao.OverallMetricsDAO;
import mx.infotec.dads.insight.pdes.exceptions.ReportException;
import mx.infotec.dads.insight.pdes.model.InfoReportTable;
import mx.infotec.dads.insight.pdes.model.PerformanceReportTable;
import mx.infotec.dads.insight.pdes.model.Report;
import mx.infotec.dads.insight.pdes.model.SizeReportTable;
import mx.infotec.dads.insight.pdes.model.WeekReportTable;
import mx.infotec.dads.insight.util.ContextUtil;
import mx.infotec.dads.insight.util.DateUtils;
import mx.infotec.dads.insight.util.UrlPd;

/**
 * WeekReportService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class WeekReportService {
    private WeekReportService() {

    }

    /**
     * 
     * @param urlPd
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static Report createReport(final UrlPd urlPd) throws ReportException {
	try {
	    Document doc = Jsoup.connect(urlPd.getWeekReportUrl().toString()).get();
	    WeekReportTable table = new WeekReportTable(doc);
	    Element element = doc.select("body table tbody tr td.left").get(1);
	    String parse = element.text();
	    String reportDate = DateUtils.extractDate(parse);
	    String toDateReportString = DateUtils.convertPdesDate(reportDate);
	    Date toDateReportDate = DateUtils.convertStringToDate(toDateReportString);
	    Date fromDateReportDate = DateUtils.moveDays(toDateReportDate, -6);
	    Document mainData = Jsoup.connect(urlPd.getGeneralReportUrl().toString()).get();
	    InfoReportTable gTable = new InfoReportTable(mainData);
	    gTable.setReportedPeriod("Del " + DateUtils.convertDateToString(fromDateReportDate) + " al "
		    + DateUtils.convertDateToString(toDateReportDate));
	    OverallMetricsDAO omDAO = new OverallMetricsDAO(urlPd);
	    SizeReportTable sizeTable = omDAO.findSizeTable("body div form table");
	    List<String> tasksInProgress = findTasksInProgress(urlPd);
	    PerformanceReportTable pTable = computeData(table);
	    gTable.setStatus(ContextUtil.computeStatus(pTable.getVgDiff()));
	    return new Report(gTable, table, pTable, sizeTable, tasksInProgress);
	} catch (Exception e) {
	    throw new ReportException("createReport", e);
	}

    }

    public static PerformanceReportTable computeData(WeekReportTable table) {
	String vg = computeVg(table);
	String vgDiff = computeVgDiff(table);
	String vgFalta = computeVgFalta(table);
	String horasTarea = computeTaskHours(table);
	String tareaCerradas = computeClosedTask(table);
	double hsTareasTerm = computeHoursTaskNotFinished(table);
	String semHrTarNoTerm = computeWeekhrsTaskNotFinished(table, hsTareasTerm);
	double vhxH = computeVgXh(table);
	String vgNoRe = computeVgNotPerformed(vhxH, hsTareasTerm);
	String recup = computeRecoveryWeeks(table);

	return PerformanceReportTable.getBuilder().withVg(vg).withVgDiff(vgDiff).withVgFalta(vgFalta)
		.withTaskHours(horasTarea).withTaskClosed(tareaCerradas)
		.withHoursNotFinished(Double.toString(hsTareasTerm)).withWeekHrsNotFinished(semHrTarNoTerm)
		.withVgPerHour(Double.toString(vhxH)).withVgNotPerformed(vgNoRe).withRecovery(recup).build();
    }

    private static List<String> findTasksInProgress(UrlPd urlPd) throws IOException, URISyntaxException {
	Document doc = Jsoup.connect(urlPd.getWeekReportUrl().toString()).get();
	Elements tasksList = doc.select("[name=dueTask]");
	if (!tasksList.isEmpty()) {
	    Elements task = doc.select("[name=dueTask]").get(0).select("td.left");
	    List<String> tasksInProgress = new ArrayList<>();
	    for (Element element : task) {
		tasksInProgress.add(element.text());
	    }
	    return tasksInProgress;
	} else {
	    return new ArrayList<>();
	}

    }

    public static String computeVg(WeekReportTable table) {
	return Double.toString(table.getDoubleProperty(1, 4));
    }

    public static String computeVgDiff(WeekReportTable table) {
	return Double.toString(table.getDoubleProperty(1, 4) - table.getDoubleProperty(1, 3));
    }

    public static String computeVgFalta(WeekReportTable table) {
	return Double.toString((1 - table.getDoubleProperty(1, 5)) * 100);
    }

    public static String computeTaskHours(WeekReportTable table) {
	return Double.toString(table.getDoubleProperty(1, 1));
    }

    public static String computeClosedTask(WeekReportTable table) {
	return Double.toString((table.getDoubleProperty(3, 2) * 100) - 100);
    }

    public static double computeHoursTaskNotFinished(WeekReportTable table) {
	return table.getDoubleProperty(1, 1) - table.getDoubleProperty(3, 1);
    }

    public static String computeWeekhrsTaskNotFinished(WeekReportTable table, double hsTareasTerm) {
	return Double.toString(hsTareasTerm / table.getDoubleProperty(2, 1));
    }

    public static double computeVgXh(WeekReportTable table) {
	return table.getDoubleProperty(1, 4) / table.getDoubleProperty(3, 1);
    }

    public static String computeVgNotPerformed(double vhxH, double hsTareasTerm) {
	return Double.toString(vhxH * hsTareasTerm);
    }

    public static String computeRecoveryWeeks(WeekReportTable table) {
	return Double.toString(
		(table.getDoubleProperty(1, 3) - table.getDoubleProperty(1, 4)) / table.getDoubleProperty(2, 4));
    }

}
