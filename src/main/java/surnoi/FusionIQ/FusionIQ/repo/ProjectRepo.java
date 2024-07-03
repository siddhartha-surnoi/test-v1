package surnoi.FusionIQ.FusionIQ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import surnoi.FusionIQ.FusionIQ.data.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
