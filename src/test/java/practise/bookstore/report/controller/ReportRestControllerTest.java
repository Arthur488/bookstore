package practise.bookstore.report.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import practise.bookstore.generics.exceptions.NotFoundException;
import practise.bookstore.report.service.ReportService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportRestController.class)
class ReportRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * annotation that can be used to replace a bean with a mock object.
     * This is useful when you want to test a specific component or module in isolation from the rest of the application and need to control the behavior of its dependencies.
     */
    @MockBean
    private ReportService reportService;

    @Test
    public void testGenerateReport() throws Exception {
        // Определяем ожидаемый результат
        Map <LocalDate, Float> expectedReportItems = new HashMap <>();
        expectedReportItems.put(LocalDate.of(2023, 1, 1), 1000.0f);
        expectedReportItems.put(LocalDate.of(2023, 1, 2), 1500.0f);

        // задем поведение сервиса
        when(reportService.generateReportBetweenTwoDates(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(expectedReportItems);

        // Отправляем фейковый запрос
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/reports/{startDate}:{endDate}", "2023.01.01", "2023.01.02")
                .accept(MediaType.ALL);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['2023-01-01']").value(1000.0))
                .andExpect(jsonPath("$['2023-01-02']").value(1500.0));

        // Проверяем, что сервисный метод был вызван с ожидаемыми аргументами
        verify(reportService).generateReportBetweenTwoDates(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2));

    }

    @Test
    public void testGenerateReportWithException() throws Exception {
        // задем поведение сервиса, обязательно указываем выкинутое исключение с сообщением
        when(reportService.generateReportBetweenTwoDates(any(LocalDate.class), any(LocalDate.class)))
                .thenThrow(new NotFoundException("Requested data is not found! No records available for the given date range!"));

        // Отправляем фейковый запрос
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/reports/{startDate}:{endDate}", "2023.01.01", "2023.01.02")
                .accept(MediaType.ALL);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(content().string("Requested data is not found! No records available for the given date range!"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));

    }
}