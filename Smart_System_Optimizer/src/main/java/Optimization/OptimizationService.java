package Optimization;

import entity.SystemMetric;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import java.io.File;

@Service
public class OptimizationService {

    public String analyze(SystemMetric metric) {

        if (metric == null) {
            return "⚠ Metric data not available";
        }

        StringBuilder result = new StringBuilder();

        double beforeCpu = metric.getCpuUsage();
        double beforeMem = metric.getMemoryUsage();

        result.append("📊 BEFORE OPTIMIZATION:\n");
        result.append("CPU: ").append(beforeCpu).append("%\n");
        result.append("Memory: ").append(beforeMem).append("%\n\n");

        result.append("⚙️ OPTIMIZATION ACTIONS:\n");

        if (beforeCpu > 80) {
            result.append("⚠ High CPU detected\n");
        }

        if (beforeMem > 75) {
            result.append("⚠ High Memory detected\n");
        }

        // GC
        System.gc();
        result.append("✅ Garbage Collection executed\n");

        // Temp clean
        result.append(cleanTempFiles());

        // 🔥 REAL AFTER METRICS (OSHI)
        double afterCpu = getCpuUsage();
        double afterMem = getMemoryUsage();

        result.append("\n📊 AFTER OPTIMIZATION:\n");
        result.append("CPU: ").append(String.format("%.2f", afterCpu)).append("%\n");
        result.append("Memory: ").append(String.format("%.2f", afterMem)).append("%\n");

        // Improvement
        result.append("\n📈 IMPROVEMENT:\n");
        result.append("CPU Reduced: ")
                .append(String.format("%.2f", (beforeCpu - afterCpu)))
                .append("%\n");

        result.append("Memory Reduced: ")
                .append(String.format("%.2f", (beforeMem - afterMem)))
                .append("%\n");

        result.append("\n✅ Optimization completed\n");

        return result.toString();
    }

    // ---------------- TEMP CLEAN ----------------
    private String cleanTempFiles() {

        StringBuilder result = new StringBuilder();

        try {
            String tempPath = System.getProperty("java.io.tmpdir");
            File tempDir = new File(tempPath);

            int deleted = 0;

            if (tempDir.exists()) {
                File[] files = tempDir.listFiles();

                if (files != null) {
                    for (File f : files) {
                        if (f.isFile() && f.length() < 5 * 1024 * 1024) {
                            if (f.delete()) deleted++;
                        }
                    }
                }
            }

            result.append("🧹 Temp files deleted: ").append(deleted).append("\n");

        } catch (Exception e) {
            result.append("⚠ Temp cleanup failed\n");
        }

        return result.toString();
    }

    // ---------------- CPU ----------------
    private double getCpuUsage() {
        SystemInfo si = new SystemInfo();
        CentralProcessor cpu = si.getHardware().getProcessor();

        long[] prevTicks = cpu.getSystemCpuLoadTicks();
        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {}

        double load = cpu.getSystemCpuLoadBetweenTicks(prevTicks);
        return load * 100;
    }

    // ---------------- MEMORY ----------------
    private double getMemoryUsage() {
        SystemInfo si = new SystemInfo();
        GlobalMemory memory = si.getHardware().getMemory();

        double used = memory.getTotal() - memory.getAvailable();
        return (used / memory.getTotal()) * 100;
    }
}