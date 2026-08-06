package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SystemInfoController {

    @GetMapping("/system/info")
    public Map<String, Object> systemInfo() throws Exception {

        Map<String, Object> response = new LinkedHashMap<>();

        Runtime runtime = Runtime.getRuntime();

        File disk = new File(".");

        long totalDisk = disk.getTotalSpace();
        long freeDisk = disk.getFreeSpace();
        long usedDisk = totalDisk - freeDisk;

        double diskUsage =
                ((double) usedDisk / totalDisk) * 100;

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = allocatedMemory - freeMemory;

        response.put("application", "Employee Service");
        response.put("status", "UP");
        response.put("version", "1.0.0");
        response.put("environment", "Production");

        response.put("javaVersion",
                System.getProperty("java.version"));

        response.put("os",
                System.getProperty("os.name"));

        response.put("hostName",
                InetAddress.getLocalHost().getHostName());

        response.put("serverTime",
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Map<String, Object> diskInfo = new LinkedHashMap<>();

        diskInfo.put("total", formatGB(totalDisk));
        diskInfo.put("free", formatGB(freeDisk));
        diskInfo.put("used", formatGB(usedDisk));
        diskInfo.put("usage",
                new DecimalFormat("#0.00").format(diskUsage) + "%");

        response.put("disk", diskInfo);

        Map<String, Object> memoryInfo = new LinkedHashMap<>();

        memoryInfo.put("max", formatMB(maxMemory));
        memoryInfo.put("allocated", formatMB(allocatedMemory));
        memoryInfo.put("free", formatMB(freeMemory));
        memoryInfo.put("used", formatMB(usedMemory));

        response.put("memory", memoryInfo);

        Map<String, Object> cpu = new LinkedHashMap<>();

        cpu.put("availableProcessors",
                runtime.availableProcessors());

        response.put("cpu", cpu);

        return response;
    }

    private String formatGB(long bytes) {

        return new DecimalFormat("#0.00")
                .format(bytes / 1024.0 / 1024 / 1024)
                + " GB";
    }

    private String formatMB(long bytes) {

        return new DecimalFormat("#0.00")
                .format(bytes / 1024.0 / 1024)
                + " MB";
    }

}