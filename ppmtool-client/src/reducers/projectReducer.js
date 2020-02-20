import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function(state = initialState.projects, action) {
    switch (action.type) {
        case types.GET_PROJECTS:
            return action.projects;
        case types.CREATE_PROJECT_SUCCESS:
            return [...state, { ...action.project }];
        case types.UPDATE_PROJECT_SUCCESS:
            return state.map(project =>
                project.id === action.project.id ? action.project : project
            );
        case types.DELETE_PROJECT:
            return state.filter(
                project => project.projectIdentifier !== action.id
            );
        default:
            return state;
    }
}
