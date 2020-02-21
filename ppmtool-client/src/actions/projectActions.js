import * as types from './actionTypes';
import * as projectApi from '../api/projectApi';

export function loadProjectsSuccess(projects) {
    return { type: types.GET_PROJECTS, projects };
}

export function createProjectSuccess(project) {
    return { type: types.CREATE_PROJECT_SUCCESS, project };
}

export function updateProjectSuccess(project) {
    return { type: types.UPDATE_PROJECT_SUCCESS, project };
}

export function deleteProjectOptimistic(id) {
    return { type: types.DELETE_PROJECT, id };
}

export const getProjects = () => async dispatch => {
    return projectApi
        .loadProjects()
        .then(projects => {
            dispatch(loadProjectsSuccess(projects));
        })
        .catch(error => {
            throw error;
        });
};

export const createProject = project => async dispatch => {
    return projectApi
        .saveProject(project)
        .then(savedProject => {
            project.id
                ? dispatch(updateProjectSuccess(savedProject))
                : dispatch(createProjectSuccess(savedProject));
        })
        .catch(error => {
            throw error;
        });
};

export const deleteProject = id => async dispatch => {
    dispatch(deleteProjectOptimistic(id));
    return projectApi.deleteProject(id);
};
