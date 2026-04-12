package service;



import entity.SystemMetric;
import repository.SystemMetricRepository;
import system.SystemMetricsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricsService {

    private final SystemMetricRepository repository;
    private final SystemMetricsService systemMetricsService;

    // Constructor Injection
    public MetricsService(SystemMetricRepository repository,
                          SystemMetricsService systemMetricsService) {
        this.repository = repository;
        this.systemMetricsService = systemMetricsService;
    }

    //  Collect + Save
    public SystemMetric collectAndSaveMetrics() {
        double cpu = systemMetricsService.getCpuUsage();
        double memory = systemMetricsService.getMemoryUsage();

        SystemMetric metric = new SystemMetric();
        metric.setCpuUsage(cpu);
        metric.setMemoryUsage(memory);
        metric.setTimestamp(LocalDateTime.now());

        return repository.save(metric);
    }

    // Get all
    public List<SystemMetric> getAllMetrics() {
        return repository.findAll();
    }

    //  Get latest (optimized)
    public SystemMetric getLatestMetric() {
        return repository.findTopByOrderByTimestampDesc();
    }
}