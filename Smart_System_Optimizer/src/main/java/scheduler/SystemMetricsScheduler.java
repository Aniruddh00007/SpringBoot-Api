package scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import service.MetricsService;

@Component
public class SystemMetricsScheduler {

    private final MetricsService metricsService;

    // Constructor Injection
    public SystemMetricsScheduler(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    //  Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void run() {

        metricsService.collectAndSaveMetrics();

        System.out.println("Metrics collected & saved successfully!");
    }
}