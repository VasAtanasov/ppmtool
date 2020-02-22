import http from '../utils/http';
import { handleResponse, handleError } from './apiUtils';

export const BACKLOG_URL = '/api/backlog';

export const saveProjectTask = async (projectIdentifier, projectTask) => {
    try {
        const res = await http.post(`${BACKLOG_URL}/${projectIdentifier}`, {
            data: projectTask
        });
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
