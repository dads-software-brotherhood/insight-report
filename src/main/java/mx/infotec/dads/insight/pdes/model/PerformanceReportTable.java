package mx.infotec.dads.insight.pdes.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Clase que representa la abstracción del reporte de desempeño.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class PerformanceReportTable {

    private String vg;
    private String vgDiff;
    private String vgFalta;
    private String taskHours;
    private String taskClosed;
    private String hoursNotFinished;
    private String weekHrsNotFinished;
    private String vgPerHour;
    private String vgNotPerformed;
    private String recovery;

    private PerformanceReportTable() {

    }

    public static PerformanceTableBuilder getBuilder() {
	return new PerformanceTableBuilder(new PerformanceReportTable());
    }

    public String getVg() {
	return vg;
    }

    public void setVg(String vg) {
	this.vg = vg;
    }

    public String getTaskHours() {
	return taskHours;
    }

    public void setTaskHours(String taskHours) {
	this.taskHours = taskHours;
    }

    public String getTaskClosed() {
	return taskClosed;
    }

    public void setTaskClosed(String taskClosed) {
	this.taskClosed = taskClosed;
    }

    public String getHoursNotFinished() {
	return hoursNotFinished;
    }

    public void setHoursNotFinished(String hoursNotFinished) {
	this.hoursNotFinished = hoursNotFinished;
    }

    public String getWeekHrsNotFinished() {
	return weekHrsNotFinished;
    }

    public void setWeekHrsNotFinished(String weekHrsNotFinished) {
	this.weekHrsNotFinished = weekHrsNotFinished;
    }

    public String getVgPerHour() {
	return vgPerHour;
    }

    public void setVgPerHour(String vgPerHour) {
	this.vgPerHour = vgPerHour;
    }

    public String getVgNotPerformed() {
	return vgNotPerformed;
    }

    public void setVgNotPerformed(String vgNotPerformed) {
	this.vgNotPerformed = vgNotPerformed;
    }

    public String getRecovery() {
	return recovery;
    }

    public void setRecovery(String recovery) {
	this.recovery = recovery;
    }

    public String getVgDiff() {
	return vgDiff;
    }

    public void setVgDiff(String vgDiff) {
	this.vgDiff = vgDiff;
    }

    public String getVgFalta() {
	return vgFalta;
    }

    public void setVgFalta(String vgFalta) {
	this.vgFalta = vgFalta;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

    /**
     * Builder for the class PerformTable
     * 
     * @author Daniel Cortes Pichardo
     *
     */
    public static class PerformanceTableBuilder {
	private PerformanceReportTable pt;

	public PerformanceTableBuilder(PerformanceReportTable pt) {
	    this.pt = pt;
	}

	public PerformanceTableBuilder withVg(String vg) {
	    pt.setVg(vg);
	    return this;
	}

	public PerformanceTableBuilder withTaskHours(String taskHours) {
	    pt.setTaskHours(taskHours);
	    return this;
	}

	public PerformanceTableBuilder withTaskClosed(String taskClosed) {
	    pt.setTaskClosed(taskClosed);
	    return this;
	}

	public PerformanceTableBuilder withHoursNotFinished(String hoursNotFinished) {
	    pt.setHoursNotFinished(hoursNotFinished);
	    return this;
	}

	public PerformanceTableBuilder withWeekHrsNotFinished(String weekHrsNotFinished) {
	    pt.setWeekHrsNotFinished(weekHrsNotFinished);
	    return this;
	}

	public PerformanceTableBuilder withVgPerHour(String vgPerHour) {
	    pt.setVgPerHour(vgPerHour);
	    return this;
	}

	public PerformanceTableBuilder withVgNotPerformed(String vgNotPerformed) {
	    pt.setVgNotPerformed(vgNotPerformed);
	    return this;
	}

	public PerformanceTableBuilder withRecovery(String recovery) {
	    pt.setRecovery(recovery);
	    return this;
	}

	public PerformanceTableBuilder withVgDiff(String vgDiff) {
	    pt.setVgDiff(vgDiff);
	    return this;
	}

	public PerformanceTableBuilder withVgFalta(String vgFalta) {
	    pt.setVgFalta(vgFalta);
	    return this;
	}

	public PerformanceReportTable build() {
	    return this.pt;
	}
    }
}
