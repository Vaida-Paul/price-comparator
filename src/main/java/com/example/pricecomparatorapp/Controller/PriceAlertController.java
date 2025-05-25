package com.example.pricecomparatorapp.Controller;

import com.example.pricecomparatorapp.Dto.CreatePriceAlertRequest;
import com.example.pricecomparatorapp.Dto.PriceAlertDto;
import com.example.pricecomparatorapp.Service.PriceAlertService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@SecurityRequirement(name = "bearerAuth")
public class PriceAlertController {
    private final PriceAlertService priceAlertService;
    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    /**
     * Creates a new price alert.
     *
     * @param request The request containing the details for the price alert.
     * @return The created price alert.
     */
    @PostMapping
    public PriceAlertDto createPriceAlert(@Valid @RequestBody CreatePriceAlertRequest request) {
        return priceAlertService.createPriceAlert(request);
    }
    /**
     * Retrieves all price alerts for a specific user.
     *
     * @param userId The ID of the user whose alerts are to be retrieved.
     * @return A list of price alerts for the specified user.
     */
    @GetMapping("/user/{userId}")
    public List<PriceAlertDto> getUserAlerts(@PathVariable Long userId) {
        return priceAlertService.getUserAlerts(userId);
    }
}