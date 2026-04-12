package system;


import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@Service
public class SystemMetricsService {

    private final SystemInfo systemInfo = new SystemInfo();

    // CPU Usage
    public double getCpuUsage() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();

        try {
            Thread.sleep(1000); // small delay for accurate reading
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
    }

    // Memory Usage
    public double getMemoryUsage() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long total = memory.getTotal();
        long available = memory.getAvailable();

        return ((double) (total - available) / total) * 100;
    }
}