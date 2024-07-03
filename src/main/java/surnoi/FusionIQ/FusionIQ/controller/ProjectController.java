package surnoi.FusionIQ.FusionIQ.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surnoi.FusionIQ.FusionIQ.data.Project;
import surnoi.FusionIQ.FusionIQ.repo.ProjectRepo;
import surnoi.FusionIQ.FusionIQ.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {
    @Autowired
    ProjectRepo projectRepo;
    @Autowired
    ProjectService projectService;

    @PostMapping("/project")

    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectRepo.save(project), HttpStatus.CREATED);
    }

    @GetMapping("/allusers")
    public List<Project> getAllUsers() {
        return projectService.getAllUsers();
    }
//    @PutMapping("update/projects/{id}")
//    public ResponseEntity<Project> updateProject(@PathVariable long id, Project project) {
//        Optional<Project> project1 = projectRepo.findById(id);
//        if (project1.isPresent()) {
//            Project existingFee = project1.get();
//            existingFee.setCourseTitle(project.getCourseTitle());
//            existingFee.setMethodology(project.getMethodology());
//            existingFee.setProjectOverview(project.getProjectOverview());
//            existingFee.setObjectives(project.getObjectives());
//            existingFee.setScope(project.getScope());
//            existingFee.setTimeLine(project.getTimeLine());
//            existingFee.setProjectTitle(project.getProjectTitle());
//
//            return new ResponseEntity<>(projectRepo.save(existingFee), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

    @DeleteMapping("delete/project/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable long id) {
        Optional<Project> fee = projectRepo.findById(id);
        if (fee.isPresent()) {
            projectRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("project/get/{id}")
    public ResponseEntity<Project> getFeeById(@PathVariable long id) {
        Optional<Project> fee = projectRepo.findById(id);
        if (fee.isPresent()) {
            return new ResponseEntity<>(fee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId, @RequestBody Project updatedProject) {
        Project project = projectService.getProjectById(projectId);
        if
        (project == null) {
            return new
                    ResponseEntity
                            <>(HttpStatus.NOT_FOUND);
        }
        project.setProjectTitle(updatedProject.getProjectTitle());
        project.setProjectOverview(updatedProject.getProjectOverview());
        project.setObjectives(updatedProject.getObjectives());
        project.setScope(updatedProject.getScope());
        project.setTimeLine(updatedProject.getTimeLine());
        project.setMethodology(updatedProject.getMethodology());
        project.setCourseTitle(updatedProject.getCourseTitle());
        project = projectService.saveProject(project);
        return
                new
                        ResponseEntity
                                <>(project, HttpStatus.OK);
    }
}


