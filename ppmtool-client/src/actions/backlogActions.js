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

export function deleteProjectTaskOptimistic(taskSequence) {
    return { type: types.DELETE_PROJECT_TASK, taskSequence };
}

export const loadBacklog = projectIdentifier => async dispatch => {
    try {
        const backlog = await backlogApi.getBacklog(projectIdentifier);
        dispatch(loadBacklogSuccess(backlog));
    } catch (error) {
        throw error;
    }
};

export const createProjectTask = (
    projectIdentifier,
    projectTask
) => async dispatch => {
    try {
        const savedProjectTask = await backlogApi.saveProjectTask(
            projectIdentifier,
            projectTask
        );
        savedProjectTask.id
            ? dispatch(createProjectTaskSuccess(savedProjectTask))
            : dispatch(updateProjectTaskSuccess(savedProjectTask));
    } catch (error) {
        throw error;
    }
};

export const deleteProjectTask = (
    projectIdentifier,
    taskSequence
) => async dispatch => {
    dispatch(deleteProjectTaskOptimistic(taskSequence));
    return backlogApi.deleteProjectTask(projectIdentifier, taskSequence);
};
