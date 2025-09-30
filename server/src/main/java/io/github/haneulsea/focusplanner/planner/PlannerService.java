package io.github.haneulsea.focusplanner.planner;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerService(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    @Transactional
    public Planner createPlanner(Planner planner) {
        return plannerRepository.save(planner);
    }

    @Transactional(readOnly = true)
    public List<Planner> getAllPlanners() {
        return plannerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Planner getPlannerById(Integer plannerId) {
        return plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));
    }

    @Transactional
    public Planner updatePlannerById(Integer plannerId, Map<String, Object> updates) {
        Planner planner = plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));

        // Add a loop and switch when more fields are patchable
        // Example fields: createdAt, completed, etc.
        if (updates.containsKey("title")) {
            planner.setTitle((String) updates.get("title"));
        }

        return planner;
    }

    @Transactional
    public void deletePlannerById(Integer plannerId) {
        Planner planner = plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));

        plannerRepository.delete(planner);
    }

}
