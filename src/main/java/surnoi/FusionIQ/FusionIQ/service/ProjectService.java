package surnoi.FusionIQ.FusionIQ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import surnoi.FusionIQ.FusionIQ.data.Project;
import surnoi.FusionIQ.FusionIQ.repo.ProjectRepo;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    public List<Project> getAllUsers() {
        return projectRepo.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepo.findById(projectId).orElse(null);
    }
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }
}
//    public List<Project> getAllUsers() {
//        return projectRepo.findAll();
//    }
//
//    public void deleteProject(Long id) {
//    }
//}

