/*
 *
 * Licencia: Usted puede utilizar libremente este cÃ³digo para copiarlo, distribuirlo o modificarlo
 * total o parcialmente siempre y cuando mantenga este aviso y reconozca la autorÃ­a del código al
 * no modificar los datos establecidos en la menciÃ³n de "AUTOR".
 *
 * Proyecto: dnias-sep Paquete: mx.com.infotec.dadt.arq.core.support Módulo: Utils Tipo: Clase de
 * utilerÃ­as Autor: Daniel Cortes Pichardo
 *
 * Historia: 23/12/2014 Creación.
 */
package mx.infotec.dads.insight.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

/**
 * DateUtil Class is a Wrapper of useful libraries for dates operation, meanly
 * joda and objectlab.
 * <p>
 * This Class has a useful methods that join the power of both libraries and in
 * all the operation java.util.Date is considered like the principal component
 * 
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    private static boolean isEn = true;

    private static final DateTimeFormatter PDES_EN_TIME_FORMATTER = DateTimeFormat
	    .forPattern(Constants.PDES_EN_DATE_FORMAT_PATTERN);
    private static final DateTimeFormatter PDES_ES_TIME_FORMATTER = DateTimeFormat
	    .forPattern(Constants.PDES_ES_DATE_FORMAT_PATTERN);
    private static final DateTimeFormatter PDES_TIME_FOLDER_DATE_FORMAT = DateTimeFormat
	    .forPattern(Constants.PDES_FOLDER_DATE_FORMAT);
    /*
     * Expresión regular utilizada por el motor de permisos RegExp =
     * ^P((.*))([&|])E((.*))([&|])FIG((.*))([&|])PO()$
     */
    private static final String REG_EXP = "[0-9][0-9]?/[0-9][0-9]?/[0-9][0-9]";
    private static final Pattern PDES_DATE_PATTERN = Pattern.compile(REG_EXP);

    private DateUtils() {
    }

    /**
     * Calculate the number of days between two Dates without consider Holidays
     * and weekends.
     * 
     * @param from
     *            the initial date for the operation
     * @return the number of days between Dates.
     * @param to
     *            the final date for the operation
     */
    public static int daysBetween(Date from, Date to) {
	LocalDate fromDate = new LocalDate(from);
	LocalDate toDate = new LocalDate(to);
	return Days.daysBetween(fromDate, toDate).getDays();
    }

    /**
     * Calculate the number of days to New Year.
     * 
     * @param from
     *            the initial date for the operation
     * 
     * @return the number of days to New Year.
     */
    public static int daysToNewYearFrom(Date from) {
	LocalDate fromDate = new LocalDate(from);
	LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
	return Days.daysBetween(fromDate, newYear).getDays();
    }

    public static Date moveDays(Date from, int numberOfDays) {
	DateCalculator<LocalDate> cal = LocalDateKitCalculatorsFactory.forwardCalculator("APP-HOLYDAY");
	cal.setStartDate(new LocalDate(from));
	LocalDate newDate = cal.moveByDays(numberOfDays).getCurrentBusinessDate();
	return newDate.toDateTimeAtStartOfDay().toDate();
    }

    /**
     * Convert string to date.
     *
     * <p>
     * Convierte un objeto java.lang.Date a su cadena representativa utilizando
     * el formato definido en la constante
     * mx.com.infotec.dadt.arq.core.support.Constants.DATE_FORMAT_PATTERN
     * 
     * @param dateToFormat
     *            la fecha que se desea convertir
     * @return la cadena representativa de la fecha
     */
    public static String convertDateToString(Date dateToFormat) {
	return getCurrentDateFormater().print(new DateTime(dateToFormat));
    }

    /**
     * Convert date to string.
     *
     * <p>
     * Convierte un objeto java.lang.String a java.lang.Date utilizando el
     * formato definido en la constante
     * mx.com.infotec.dadt.arq.core.support.Constants.DATE_FORMAT_PATTERN
     * 
     * @param dateString
     *            la fecha que se desea convertir.
     * @return la fecha
     */
    public static Date convertStringToDate(String dateString) {
	return getCurrentDateFormater().parseDateTime(dateString).toDate();
    }

    public static Date moveDaysWithHolidays(Date initialDate, int days) {
	ValidationUtils.checkNotNull(initialDate, "La fecha inicial No puede ser Null!");
	ValidationUtils.checkCondition(days >= 0, "los dias tiene que ser mayor o igual a cero");
	DateCalculator<LocalDate> dateCal = DateCalculatorFactory.getCurrentInstance();
	dateCal.setStartDate(new LocalDate(initialDate));
	return dateCal.moveByBusinessDays(days).getStartDate().toDate();
    }

    public static String extractDate(String cad) {
	Matcher m = PDES_DATE_PATTERN.matcher(cad);
	if (m.find()) {
	    return m.group();
	} else {
	    return null;
	}
    }

    public static String convertPdesDate(String date) {
	return convertDateToString(getCurrentDateFormater().parseDateTime(date).toDate());
    }

    public static Date tryPdesEs(String date) {
	try {
	    return PDES_ES_TIME_FORMATTER.parseDateTime(date).toDate();
	} catch (Exception e) {
	    LOGGER.info("tryPdesEs", e);
	}
	return null;
    }

    public static String getDateFolderForma(Date dateToFormat) {
	return PDES_TIME_FOLDER_DATE_FORMAT.print(new DateTime(dateToFormat));
    }

    private static DateTimeFormatter getCurrentDateFormater() {
	if (isEn) {
	    return PDES_EN_TIME_FORMATTER;
	} else {
	    return PDES_ES_TIME_FORMATTER;
	}
    }

    public static boolean isEn() {
	return isEn;
    }

    public static void setEn(boolean isEn) {
	DateUtils.isEn = isEn;
    }
}
