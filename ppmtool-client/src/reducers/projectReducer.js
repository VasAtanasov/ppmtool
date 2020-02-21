import * as types from '../actions/actionTypes';
import initialState from './initialState';

const mapGetProjectsData = page => {
    return page.content;
};

export default (state = initialState.projects, action) => {
    switch (action.type) {
        case types.GET_PROJECTS:
            return mapGetProjectsData(action.projects);
        case types.CREATE_PROJECT_SUCCESS:
            return [...state, { ...action.project }];
        case types.UPDATE_PROJECT_SUCCESS:
            return state.map(project => {
                return project.id === action.project.id
                    ? action.project
                    : project;
            });
        case types.DELETE_PROJECT:
            return state.filter(project => project.id !== action.id);
        default:
            return state;
    }
};
