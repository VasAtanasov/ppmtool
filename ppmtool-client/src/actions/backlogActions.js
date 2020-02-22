import * as types from './actionTypes';
import * as backlogApi from '../api/backlogApi';

export const loadBacklogSuccess = backlog => {
    return { type: types.GET_BACKLOG, backlog };
};

export function createProjectTaskSuccess(project) {
    return { type: types.CREATE_PROJECT_TASK_SUCCESS, project };
}

export function updateProjectTaskSuccess(project) {
    return { type: types.UPDATE_PROJECT_TASK_SUCCESS, project };
}

export const loadBacklog = projectIdentifier => dispatch => {
    return backlogApi
        .getBacklog(projectIdentifier)
        .then(backlog => {
            dispatch(loadBacklogSuccess(backlog));
        })
        .catch(error => {
            throw error;
        });
};

export const createProjectTask = (
    projectIdentifier,
    projectTask
) => async dispatch => {
    return backlogApi
        .saveProject(projectIdentifier, projectTask)
        .then(savedProjectTask => {
            savedProjectTask.id
                ? dispatch(createProjectTaskSuccess(savedProjectTask))
                : dispatch(updateProjectTaskSuccess(savedProjectTask));
        })
        .catch(error => {
            throw error;
        });
};
