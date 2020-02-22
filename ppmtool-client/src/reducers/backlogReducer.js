import * as types from '../actions/actionTypes';

const initialState = {
    project_tasks: [],
    project_task: {}
};

export default function(state = initialState, action) {
    switch (action.type) {
        case types.GET_BACKLOG:
            return {
                ...state,
                project_tasks: action.payload
            };

        case types.GET_PROJECT_TASK:
            return {
                ...state,
                project_task: action.payload
            };

        case types.DELETE_PROJECT_TASK:
            return {
                ...state

                // TO_DO
            };

        default:
            return state;
    }
}
