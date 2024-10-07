package practise.bookstore.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practise.bookstore.report.service.ReportService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "RESTful Web Services for Reports", description = "Endpoints for managing reports")
public class ReportRestController {

    private final ReportService reportService;

    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{startDate}:{endDate}")
    @Operation(summary = "Generate report from date to date", description = "Report contains information about all paid amount of money for each day, during specific period of time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Model(s) Not Found", content = @Content(schema = @Schema(implementation = Map.class))),
    })
    public ResponseEntity <Map <LocalDate, Float>> generateReport(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate startDate,
                                                                  @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate endDate) {
        Map <LocalDate, Float> datePriceMap = reportService.generateReportBetweenTwoDates(startDate, endDate);
        return ResponseEntity.ok(datePriceMap);
    }


}
