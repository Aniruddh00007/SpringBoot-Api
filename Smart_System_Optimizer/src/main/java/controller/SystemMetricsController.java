package controller;



import entity.SystemMetric;
import service.MetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin
public class SystemMetricsController {

    private final MetricsService metricsService;

    public SystemMetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    //  1. Get all metrics
    @GetMapping
    public List<SystemMetric> getAllMetrics() {
        return metricsService.getAllMetrics();
    }

    //  2. Get latest metric (IMPORTANT)
    @GetMapping("/latest")
    public SystemMetric getLatestMetric() {
        return metricsService.getLatestMetric();
    }

    // 3. Manual trigger (for testing)
    @PostMapping("/collect")
    public SystemMetric collectNow() {
        return metricsService.collectAndSaveMetrics();
    }

    // 4. Health check
    @GetMapping("/health")
    public String health() {
        return "Backend is running 🚀";
    }
}