package Optimization;

import entity.SystemMetric;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.File;
import java.util.List;

@Service
public class OptimizationService {

    public String analyze(SystemMetric metric) {

        StringBuilder result = new StringBuilder();

        // 🔥 CPU Check
        if (metric.getCpuUsage() > 80) {
            result.append("⚠ High CPU usage detected. Close heavy applications.\n");
        }

        // 🔥 Memory Check
        if (metric.getMemoryUsage() > 75) {
            result.append("⚠ High Memory usage. Free some RAM.\n");
        }

        // 🔥 JVM Memory Cleanup
        System.gc();
        result.append("✅ Garbage Collection executed.\n");

        // 🔥 Temp File Cleanup (SAFE)
        String tempPath = System.getProperty("java.io.tmpdir");
        File tempDir = new File(tempPath);

        int deletedFiles = 0;

        if (tempDir.exists() && tempDir.isDirectory()) {
            File[] files = tempDir.listFiles();

            if (files != null) {
                for (File file : files) {
                    try {
                        // Only delete normal files (safe)
                        if (file.isFile() && file.delete()) {
                            deletedFiles++;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }

        result.append("🧹 Temp files deleted: ").append(deletedFiles).append("\n");

        //  OSHI 6.4.0: Top CPU Processes (FINAL CORRECT WAY)
        try {
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();

            //  Correct method for OSHI 6.4.0
            List<OSProcess> processes = os.getProcesses();

            result.append("\n🔥 Top CPU-consuming processes:\n");

            processes.stream()
                    .filter(p -> p.getProcessCpuLoadCumulative() > 0.01) // ignore idle processes
                    .sorted((p1, p2) -> Double.compare(
                            p2.getProcessCpuLoadCumulative(),
                            p1.getProcessCpuLoadCumulative()
                    ))
                    .limit(5)
                    .forEach(p -> {
                        result.append("➡ ")
                                .append(p.getName())
                                .append(" | CPU: ")
                                .append(String.format("%.2f", p.getProcessCpuLoadCumulative() * 100))
                                .append("%\n");
                    });

        } catch (Exception e) {
            result.append("⚠ Unable to fetch process details.\n");
        }

        // 🔥 Default message
        if (result.length() == 0) {
            return "✅ System running smoothly.";
        }

        return result.toString();
    }
}