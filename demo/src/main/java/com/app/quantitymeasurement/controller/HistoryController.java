package com.app.quantitymeasurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.entities.History;
import com.app.quantitymeasurement.service.HistoryService;
@CrossOrigin(origins = "*")
@RestController
public class HistoryController {
	@Autowired
	public HistoryService historyService;
    @GetMapping("/history")
    public List<History> getHistory() {
        return historyService.getAllHistory();
    }
    @GetMapping("/history/type/{type}")
    public List<History> getHistoryByType(@PathVariable String type) {
        return historyService.getHistoryByType(type);
    }
	@GetMapping("/pipeline-test")
	public String pipelineTest() {
   		return "Pipeline Success";
	}
}
