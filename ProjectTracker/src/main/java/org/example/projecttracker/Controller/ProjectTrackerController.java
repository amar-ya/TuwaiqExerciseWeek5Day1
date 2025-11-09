package org.example.projecttracker.Controller;

import org.example.projecttracker.Api.ProjectTrackerApiResponse;
import org.example.projecttracker.Model.ProjectTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectTrackerController
{
    public static int ID = 1;
    ArrayList<ProjectTracker> projectTrackers = new ArrayList<>();


    @PostMapping("/add")
    public ProjectTrackerApiResponse createProject(@RequestBody ProjectTracker newProject){
        newProject.setStatus("not done");
        newProject.setID(ID++);
        projectTrackers.add(newProject);
        return new ProjectTrackerApiResponse("new project was added successfully");
    }

    @GetMapping("/get")
    public ArrayList<ProjectTracker> getAllProjects(){
        return projectTrackers;
    }

    @PutMapping("/update/{id}")
    public ProjectTrackerApiResponse updateProject(@PathVariable int id, @RequestBody ProjectTracker project){
        for (ProjectTracker t : projectTrackers) {
            if (t.getID() == id) {
                if (project.getDescription() != null)t.setDescription(project.getDescription());
                if (project.getCompanyName() != null) t.setCompanyName(project.getCompanyName());
                if (project.getTitle() != null) t.setTitle(project.getTitle());
                return new ProjectTrackerApiResponse("project was updated successfully");
            }
        }
        return new ProjectTrackerApiResponse("project was not found");
    }

    @DeleteMapping("/delete/{id}")
    public ProjectTrackerApiResponse removeProject(@PathVariable int id){
        for (ProjectTracker t : projectTrackers) {
            if (t.getID() == id) {
                projectTrackers.remove(t);
                return new ProjectTrackerApiResponse("project was removed successfully");
            }
        }
        return new ProjectTrackerApiResponse("project was not found");
    }

    @PutMapping("/done/{id}")
    public ProjectTrackerApiResponse projectDone(@PathVariable int id){
        for (ProjectTracker t : projectTrackers) {
            if (t.getID() == id) {
                if (t.getStatus().equals("done")){
                    return new ProjectTrackerApiResponse("project was done already");
                }else {
                    t.setStatus("done");
                    return new ProjectTrackerApiResponse("project was done successfully");
                }
            }
        }
        return new ProjectTrackerApiResponse("project was not found");
    }


    @GetMapping("/get/title/{title}")
    public ProjectTracker searchProjectByTitle(@PathVariable String title){
        for (ProjectTracker t : projectTrackers) {
            if (t.getTitle().equals(title)){
                return t;
            }
        }
        return null;
    }

    @GetMapping("/get/company/{companyName}")
    public ArrayList<ProjectTracker> searchByCompanyName(@PathVariable String companyName){
        ArrayList<ProjectTracker> result = new ArrayList<>();
        for (ProjectTracker t : projectTrackers) {
            if (t.getCompanyName().equals(companyName)){
                result.add(t);
            }
        }
        return result;
    }
}
