package mx.infotec.dads.insight.pdes.model;

import java.util.List;
/**
 * Clase que encapsulta todo el reporte
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Report {

    private WeekReportTable weekReportTable;
    private PerformanceReportTable performanceReportTable;
    private InfoReportTable infoReportTable;
    private SizeReportTable sizeReportTable;
    private List<String> tasksInProgress;

    public Report(InfoReportTable gTable, WeekReportTable weekTable, PerformanceReportTable performanceTable,
	    SizeReportTable sizeTable, List<String> tasksInProgress) {
	this.infoReportTable = gTable;
	this.weekReportTable = weekTable;
	this.performanceReportTable = performanceTable;
	this.sizeReportTable = sizeTable;
	this.tasksInProgress = tasksInProgress;
    }

    public WeekReportTable getWeekReportTable() {
	return weekReportTable;
    }

    public void setWeekReportTable(WeekReportTable weekReportTable) {
	this.weekReportTable = weekReportTable;
    }

    public PerformanceReportTable getPerformanceReportTable() {
	return performanceReportTable;
    }

    public void setPerformanceReportTable(PerformanceReportTable performanceReportTable) {
	this.performanceReportTable = performanceReportTable;
    }

    public InfoReportTable getInfoReportTable() {
	return infoReportTable;
    }

    public void setInfoReportTable(InfoReportTable infoReportTable) {
	this.infoReportTable = infoReportTable;
    }

    public SizeReportTable getSizeReportTable() {
	return sizeReportTable;
    }

    public void setSizeReportTable(SizeReportTable sizeReportTable) {
	this.sizeReportTable = sizeReportTable;
    }

    public List<String> getTasksInProgress() {
	return tasksInProgress;
    }

    public void setTasksInProgress(List<String> tasksInProgress) {
	this.tasksInProgress = tasksInProgress;
    }
}
