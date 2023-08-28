package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if (project.getStatus()==null)
        project.setStatus(Status.OPEN);

        return super.save(project.getProjectCode(),project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO object) {
        if (object.getStatus()==null){
            object.setStatus(findById(object.getProjectCode()).getStatus());
        } // when updating it goes t create html which doesn't have the current status so we need to get and assign the status back to it.
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setStatus(Status.COMPLETE);
    }

    @Override //important 02:01 at the video part-5A
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList= findAll()
                .stream()
                .filter(project -> project.getAssignedManager().equals(manager))
                .map(project -> {

                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                    int completeTaskCounts = (int) taskList.stream()
                            .filter(p->p.getProject().equals(project)
                                    && p.getTaskStatus() == (Status.COMPLETE))
                            .count();

                    int unfinishedTaskCounts = (int) taskList.stream()
                            .filter(p->p.getProject().equals(project)
                                    && p.getTaskStatus() != (Status.COMPLETE))
                            .count();

                project.setCompleteTaskCounts(completeTaskCounts);
                project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                return project;
                })
                .collect(Collectors.toList());

        return projectList;
    }
}
