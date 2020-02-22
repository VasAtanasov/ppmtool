import http from '../utils/http';
import { handleResponse, handleError } from './apiUtils';

export const PROJECTS_URL = '/api/projects';

export const loadProjects = async () => {
    try {
        let response = await http.get(`${PROJECTS_URL}`);
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};

export const saveProject = async project => {
    try {
        const res = await http.post(PROJECTS_URL, {
            data: project
        });
        return handleResponse(res);
    } catch (error) {
        return handleError(error);
    }
};

export const deleteProject = async projectIdentifier => {
    try {
        const response = await http.del(`${PROJECTS_URL}/${projectIdentifier}`);
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};

export const getProject = async projectId => {
    try {
        let response = await http.get(PROJECTS_URL + '/' + projectId);
        debugger;
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};
