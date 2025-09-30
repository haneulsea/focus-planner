package io.github.haneulsea.focusplanner;

import io.github.haneulsea.focusplanner.planner.Planner;
import io.github.haneulsea.focusplanner.planner.PlannerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlannerBootstrap implements CommandLineRunner {

    private final PlannerRepository plannerRepository;

    public PlannerBootstrap(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    @Override
    public void run(String... args) {
        if (plannerRepository.count() == 0) {
            Planner defaultPlanner = new Planner();
            defaultPlanner.setTitle("Untitled");
            plannerRepository.save(defaultPlanner);
        }
    }

}
