import * as types from './actionTypes';
import * as projectApi from '../api/projectApi';

export function loadProjectsSuccess(projects) {
    return { type: types.GET_PROJECTS, projects };
}

// export function getProjectSuccess(project) {
//     return { type: GET_PROJECT, payload: project };
// }

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

// export const createProject = (project, history) => async dispatch => {
//     try {
//         await http.post('/api/projects', { data: project });
//         history.push('/dashboard');
//         dispatch({
//             type: GET_ERRORS,
//             payload: {}
//         });
//     } catch (err) {
//         dispatch({
//             type: GET_ERRORS,
//             payload: err.response.data
//         });
//     }
// };

// export const getProject = (id, history) => async dispatch => {
//     try {
//         const res = await http.get(`/api/projects/${id}`);
//         dispatch(getProjectSuccess(res.data));
//     } catch (error) {
//         history.push('/dashboard');
//     }
// };

export const deleteProject = id => async dispatch => {
    dispatch(deleteProjectOptimistic(id));
    return projectApi.deleteProject(id);
};
