package practise.bookstore.report.service;

import practise.bookstore.generics.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.Map;

/**
 * ReportService
 * <p>
 * The {@code ReportService} interface defines the contract for generating reports based on a date range.
 */
public interface ReportService {

    /**
     * This method generates a report between the specified localDateFrom and localDateTo dates and returns a map of LocalDate as keys and Float as values representing the paid amounts per day during the given period.
     * <p>
     *
     * @param localDateFrom : The starting date of the report period.
     * @param localDateTo : The ending date of the report period.
     * @return a map of LocalDate as keys and Float as values representing the paid amounts per day during the given period.
     * @throws NotFoundException if no records are available for the given date range.
     */
    Map <LocalDate, Float> generateReportBetweenTwoDates(LocalDate localDateFrom, LocalDate localDateTo) throws NotFoundException;

}
