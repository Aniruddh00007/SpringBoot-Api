package Optimization;

import entity.SystemMetric;
import org.springframework.stereotype.Service;

@Service
public class OptimizationService {

    public String analyze(SystemMetric metric) {

        if (metric.getCpuUsage() > 80) {
            return " High CPU usage! Close heavy applications.";
        }

        if (metric.getMemoryUsage() > 75) {
            return "High Memory usage! Free some RAM.";
        }

        return " System running smoothly.";
    }
}