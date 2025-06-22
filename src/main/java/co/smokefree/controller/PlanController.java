package co.smokefree.controller;

import co.smokefree.dto.PlanDTO;
import co.smokefree.entity.Plan;
import co.smokefree.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<?> createPlan(@RequestBody PlanDTO planDTO) {
        try {
            Plan createdPlan = planService.createPlan(planDTO);
            return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Plan>> getPlansByUserId(@PathVariable("user_id") Integer userId) {
        List<Plan> plans = planService.getPlansByUserId(userId);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
}
