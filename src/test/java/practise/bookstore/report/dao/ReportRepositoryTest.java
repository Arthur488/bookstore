package practise.bookstore.report.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import practise.models.AuditModel;
import practise.models.order.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class ReportRepositoryTest {

    @Autowired
    ReportRepository reportRepository;

    private List <LocalDate> localDateInputs() {
        String startDate = "2023.06.15";
        String endDate = "2024.07.18";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate localDateFrom = LocalDate.parse(startDate, formatter);
        LocalDate localDateTo = LocalDate.parse(endDate, formatter);
        return List.of(localDateFrom, localDateTo);
    }

    @Test
    void findOrdersBetweenTwoDates() {
        List <LocalDate> localDates = localDateInputs();

        List <Order> byCreatedDateBetween = reportRepository.findByCreatedDateBetween(localDates.get(0).atStartOfDay(), localDates.get(1).atStartOfDay());
        assertThat(byCreatedDateBetween.size()).isGreaterThan(0);
    }

    @Test
    void generateReportBetweenTwoDates() {
        List <LocalDate> localDates = localDateInputs();

        List <Order> byCreatedDateBetween = reportRepository.findByCreatedDateBetween(localDates.get(0).atStartOfDay(), localDates.get(1).atStartOfDay());
        byCreatedDateBetween.sort(Comparator.comparing(AuditModel::getCreatedDate));
        byCreatedDateBetween.forEach(order -> {
            LocalDate localDate = order.getCreatedDate().toLocalDate();
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            System.out.println(localDate.format(formatter2) + ":" + order.getTotalPrice());
        });
    }


}