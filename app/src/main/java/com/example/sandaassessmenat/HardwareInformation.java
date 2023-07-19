package com.example.sandaassessmenat;

import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class HardwareInformation {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem os = systemInfo.getOperatingSystem();
        ComputerSystem computerSystem = hardware.getComputerSystem();

        // Print general information
        System.out.println("Manufacturer: " + computerSystem.getManufacturer());
        System.out.println("Model: " + computerSystem.getModel());
        System.out.println("Serial Number: " + computerSystem.getSerialNumber());

        // Print operating system information
        System.out.println("Operating System: " + os.getFamily());
        System.out.println("Version: " + os.getVersionInfo().getVersion());

        // Print processor information
        System.out.println("Processor: " + hardware.getProcessor().getProcessorIdentifier().getName());
        System.out.println("Physical Cores: " + hardware.getProcessor().getPhysicalProcessorCount());
        System.out.println("Logical Cores: " + hardware.getProcessor().getLogicalProcessorCount());

        // Print memory information
        System.out.println("Total Memory: " + hardware.getMemory().getTotal());
        System.out.println("Available Memory: " + hardware.getMemory().getAvailable());

        // Print disk information
        hardware.getDiskStores().forEach(disk ->
                System.out.println("Disk: " + disk.getName() +
                        " - Size: " + disk.getSize() +
                        " - Model: " + disk.getModel()
                ));

        // Print network interface information
        hardware.getNetworkIFs().forEach(networkIF ->
                System.out.println("Network Interface: " + networkIF.getName() +
                        " - MAC Address: " + networkIF.getMacaddr() +
                        " - IP Address: " + networkIF.getIPv4addr()
                ));
    }
}

