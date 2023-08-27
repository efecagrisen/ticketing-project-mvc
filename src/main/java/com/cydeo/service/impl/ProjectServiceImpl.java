package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
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
}
