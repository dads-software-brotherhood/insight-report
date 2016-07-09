package mx.infotec.dads.insight.util;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory;

/**
 * Date Calculator Factory se utiliza para crear una instancia de DateCalculator
 * en toda la aplicación.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DateCalculatorFactory {

    private static DateCalculator<LocalDate> dateCalculator = null;

    private DateCalculatorFactory() {

    }

    /**
     * Regresa una instancia de dateCalculator para que sea utilizada en todo el
     * sistema.
     * 
     * @return DateCalculator <LocalDate> del sistema
     */
    public static DateCalculator<LocalDate> getCurrentInstance() {
	if (dateCalculator == null) {
	    return getConfiguration();
	} else {
	    return dateCalculator;
	}
    }

    private static DateCalculator<LocalDate> getConfiguration() {
	DateCalculator<LocalDate> dateCalculator;
	Set<LocalDate> holidays = new HashSet<>();
	holidays.add(new LocalDate(2009, 12, 25));
	DefaultHolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<>(holidays);
	LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("example", holidayCalendar);
	dateCalculator = LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("example",
		HolidayHandlerType.FORWARD);
	return dateCalculator;
    }

}
