package com.smokefree.controller;

import com.smokefree.dto.StatisticsDTO;
import com.smokefree.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/statistics/{userId}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable int userId) {
        StatisticsDTO stats = statisticsService.getStatistics(userId);
        return ResponseEntity.ok(stats);
    }
}