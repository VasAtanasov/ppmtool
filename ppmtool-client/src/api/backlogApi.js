import http from '../utils/http';
import { handleResponse, handleError } from './apiUtils';

export const BACKLOG_URL = '/api/backlog';

export const saveProjectTask = async (projectIdentifier, projectTask) => {
    try {
        const call = projectTask.projectSequence ? http.patch : http.post;

        const res = await call(
            `${BACKLOG_URL}/${projectIdentifier}` +
                (projectTask.projectSequence
                    ? '/' + projectTask.projectSequence
                    : ''),
            {
                data: projectTask
            }
        );
        return handleResponse(res);
    } catch (error) {
        return handleError(error);
    }
};

export const getBacklog = async projectIdentifier => {
    try {
        const res = await http.get(`${BACKLOG_URL}/${projectIdentifier}`);
        return handleResponse(res);
    } catch (error) {
        return handleError(error);
    }
};

export const deleteProjectTask = async (projectIdentifier, taskSequence) => {
    try {
        const response = await http.del(
            `${BACKLOG_URL}/${projectIdentifier}/${taskSequence}`
        );
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};
