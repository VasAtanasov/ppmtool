import http from '../utils/http';
import { handleResponse, handleError } from './apiUtils';

const PROJECTS_URL = '/api/projects';
const PROJECTS_ALL_URL = `${PROJECTS_URL}/all`;

export const loadProjects = async () => {
    try {
        let response = await http.get(PROJECTS_ALL_URL);
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};

export const deleteProject = async projectId => {
    try {
        const response = await http.del(`${PROJECTS_URL}/${projectId}`);
        return handleResponse(response);
    } catch (error) {
        return handleError(error);
    }
};
