package com.vehicles.monitor.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.vehicles.monitor.config.YAMLConfig;
import com.vehicles.monitor.model.UpdateResult;
import com.vehicles.monitor.model.VehicleInfoRequest;
import com.vehicles.monitor.model.VehicleInfoResp;
import com.vehicles.monitor.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MonitorController {

    @Autowired
    MonitoringService monitoringService;

    @Autowired
    YAMLConfig ymlConfig;

    @RequestMapping(value = "/vehicle", method = RequestMethod.POST)
    public ResponseEntity<UpdateResult> updateVehicle(@RequestBody VehicleInfoRequest info, HttpServletResponse response) {
        int numRows = monitoringService.updateVehicleStatus(info);
        if (numRows > 0) {
            return new ResponseEntity<UpdateResult>(new UpdateResult(numRows + " Vehicles updated."), HttpStatus.OK);
        } else {
            return new ResponseEntity<UpdateResult>(new UpdateResult("Couldn't find the this Vehicle"), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping("/vehiclelist")
    public List<VehicleInfoResp> getVehicleList() {
        List list = monitoringService.getAllVehicles();
        return list;
    }
}