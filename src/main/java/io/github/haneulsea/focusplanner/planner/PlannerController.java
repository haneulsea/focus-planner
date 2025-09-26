package io.github.haneulsea.focusplanner.planner;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planners")
public class PlannerController {

    private final PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @PostMapping
    public Planner createPlanner(@RequestBody Planner planner) {
        return plannerService.createPlanner(planner);
    }

    @GetMapping
    public List<Planner> getAllPlanners() {
        return plannerService.getAllPlanners();
    }

    @GetMapping("/{plannerId}")
    public Planner getPlannerById(@PathVariable Integer plannerId) {
        return plannerService.getPlannerById(plannerId);
    }

    @PatchMapping("/{plannerId}")
    public Planner updatePlannerById(@PathVariable Integer plannerId,
                                     @RequestBody Map<String, Object> updates) {
        return plannerService.updatePlannerById(plannerId, updates);
    }

    @DeleteMapping("/{plannerId}")
    public void deletePlannerById(@PathVariable Integer plannerId) {
        plannerService.deletePlannerById(plannerId);
    }

}
