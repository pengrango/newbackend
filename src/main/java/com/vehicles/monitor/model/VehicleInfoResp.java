package com.vehicles.monitor.model;

import java.time.LocalDateTime;

public class VehicleInfoResp {

    private String vehicleId;
    private String regNr;
    private String companyName;
    private String companyAddress;
    private String connected;

    public VehicleInfoResp(String vehicleId, String regNr, String companyName, String companyAddress, String connected) {
        this.vehicleId = vehicleId;
        this.regNr = regNr;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.connected = connected;
    }

    public static VehicleInfoResp fromDomain(VehicleInfoDomain infoDomain, LocalDateTime now, int timeout) {
        return new VehicleInfoResp(infoDomain.getVehicleId(),
                infoDomain.getRegNr(),
                infoDomain.getCompanyName(),
                infoDomain.getCompanyAddress(),
                now.minusSeconds(timeout).isBefore(infoDomain.getLastUpdate()) ? "YES" : "NO");
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getConnected() {
        return connected;
    }
}

