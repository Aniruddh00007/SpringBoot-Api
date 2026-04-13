package controller;

import entity.SystemMetric;
import service.MetricsService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    //   Get latest metric
    @GetMapping("/latest")
    public SystemMetric getLatestMetric() {
        return metricsService.getLatestMetric();
    }

    //  Manual trigger (for testing)
    @PostMapping("/collect")
    public SystemMetric collectNow() {
        return metricsService.collectAndSaveMetrics();
    }

    //  Optimization API (IMPORTANT)
    @GetMapping("/optimize")
    public Map<String, Object> optimizeSystem() {

        SystemMetric metric = metricsService.collectAndSaveMetrics();
        String suggestion = metricsService.collectAnalyzeAndSave();

        Map<String, Object> response = new HashMap<>();
        response.put("cpuUsage", metric.getCpuUsage());
        response.put("memoryUsage", metric.getMemoryUsage());
        response.put("timestamp", metric.getTimestamp());
        response.put("suggestion", suggestion);

        return response;
    }

    // Health check
    @GetMapping("/health")
    public String health() {
        return "Backend is running ";
    }
}