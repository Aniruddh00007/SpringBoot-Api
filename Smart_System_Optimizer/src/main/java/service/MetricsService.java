package service;

import entity.SystemMetric;
import repository.SystemMetricRepository;
import system.SystemMetricsService;
import Optimization.OptimizationService;   

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricsService {

    private final SystemMetricRepository repository;
    private final SystemMetricsService systemMetricsService;
    private final OptimizationService optimizationService; 

    // Constructor Injection
    public MetricsService(SystemMetricRepository repository,
                          SystemMetricsService systemMetricsService,
                          OptimizationService optimizationService) {  
        this.repository = repository;
        this.systemMetricsService = systemMetricsService;
        this.optimizationService = optimizationService;
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

    // NEW: Collect + Save + Analyze
    public String collectAnalyzeAndSave() {
        SystemMetric metric = collectAndSaveMetrics();

        // Optimization logic call
        String result = optimizationService.analyze(metric);

        return result;
    }

    //  Get all data
    public List<SystemMetric> getAllMetrics() {
        return repository.findAll();
    }

    //  Get latest metric
    public SystemMetric getLatestMetric() {
        return repository.findTopByOrderByTimestampDesc();
    }
}