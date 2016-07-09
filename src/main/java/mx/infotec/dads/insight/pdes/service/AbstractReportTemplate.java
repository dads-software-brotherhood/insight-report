package mx.infotec.dads.insight.pdes.service;

import mx.infotec.dads.insight.pdes.exceptions.ReportException;
import mx.infotec.dads.insight.pdes.service.context.ReportContext;

/**
 * The AbstractReportTemplate Class used to create the Main Report
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public abstract class AbstractReportTemplate {
    /**
     * Create the week report based on the url of the process dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void createWeekReport(ReportContext context) throws ReportException;

    /**
     * Locate the Earn Value Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateEvImage(ReportContext context) throws ReportException;

    /**
     * Locate the Cummulative Direct Tieme Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateCumDirectTimeImage(ReportContext context) throws ReportException;

    /**
     * Locate the Earn Value Trend Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateEvTrendImage(ReportContext context) throws ReportException;

    /**
     * Locate the Direct Time Trend in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateDirectTimeTrendImage(ReportContext context) throws ReportException;

    /**
     * Locate the Progress Task Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateInProgressTaskImage(ReportContext context) throws ReportException;

    /**
     * Locate the Defect Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateDefectImages(ReportContext context) throws ReportException;

    /**
     * Locate the External Commitments Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateExternalCommitmentsImage(ReportContext context) throws ReportException;

    /**
     * Locate the Milestones Image in the Process Dashboard
     * 
     * @param context
     * @throws ReportException
     */
    protected abstract void locateMilestonesImage(ReportContext context) throws ReportException;

    /**
     * Method template that creates the main Report
     * 
     * @param context
     * @throws ReportException
     */
    public void createReport(ReportContext context) throws ReportException {
	createWeekReport(context);
	locateEvImage(context);
	locateCumDirectTimeImage(context);
	locateEvTrendImage(context);
	locateDirectTimeTrendImage(context);
	locateInProgressTaskImage(context);
	locateDefectImages(context);
	locateExternalCommitmentsImage(context);
	locateMilestonesImage(context);
    }
}
