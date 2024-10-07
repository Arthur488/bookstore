package practise.bookstore.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.report.dao.ReportRepository;
import practise.models.AuditModel;
import practise.models.order.Order;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code ReportServiceImpl} class implements the {@code ReportService} interface and provides the
 * implementation for generating reports.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Generates a report between the specified start and end dates. Retrieves the required data from the
     * report repository and maps the paid amounts to their corresponding dates.
     *
     * @param localDateFrom the starting date of the report period
     * @param localDateTo   the ending date of the report period
     * @return a map of dates and paid amounts per day
     * @throws NotFoundException if no records are available for the given date range
     */
    @Override
    public Map <LocalDate, Float> generateReportBetweenTwoDates(LocalDate localDateFrom, LocalDate localDateTo) {

        List <Order> byCreatedDateBetween = reportRepository.findByCreatedDateBetween(localDateFrom.atStartOfDay(), localDateTo.atStartOfDay());
        Map <LocalDate, Float> reportItems = new HashMap <>();
        byCreatedDateBetween.stream().sorted(Comparator.comparing(AuditModel::getCreatedDate))
                .forEach(order -> reportItems.put(order.getCreatedDate().toLocalDate(), order.getTotalPrice().floatValue()));

        if (reportItems.isEmpty()) {
            throw new NotFoundException("Requested data is not found! No records available for the given date range!");
        }

        return reportItems;
    }


}
