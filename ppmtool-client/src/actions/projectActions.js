import http from '../utils/http';
import {
    GET_ERRORS,
    GET_PROJECTS,
    GET_PROJECT,
    DELETE_PROJECT
} from './actionTypes';

export function getProjectSuccess(project) {
    return { type: GET_PROJECT, payload: project };
}

export function getProjectsSuccess(projects) {
    return { type: GET_PROJECTS, payload: projects };
}

export function deleteProjectOptimistic(id) {
    return { type: DELETE_PROJECT, payload: id };
}

export const createProject = (project, history) => async dispatch => {
    try {
        await http.post('/api/projects', { data: project });
        history.push('/dashboard');
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getProjects = () => async dispatch => {
    const res = await http.get('/api/projects/all');
    dispatch(getProjectsSuccess(res.data));
};

export const getProject = (id, history) => async dispatch => {
    try {
        const res = await http.get(`/api/projects/${id}`);
        dispatch(getProjectSuccess(res.data));
    } catch (error) {
        history.push('/dashboard');
    }
};

export const deleteProject = id => async dispatch => {
    if (
        window.confirm(
            'Are you sure? This will delete the project and all the data related to it'
        )
    ) {
        await http.del(`/api/projects/${id}`);
        dispatch(deleteProjectOptimistic(id));
    }
};
