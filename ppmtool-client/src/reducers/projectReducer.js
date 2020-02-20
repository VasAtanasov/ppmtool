import * as types from '../actions/actionTypes';

const initialState = [];

export default function(state = initialState, action) {
    switch (action.type) {
        case types.GET_PROJECTS:
            return action.projects;

        // case GET_PROJECT:
        //     debugger;
        //     return {
        //         ...state,
        //         project: action.payload
        //     };

        case types.DELETE_PROJECT:
            return state.filter(
                project => project.projectIdentifier !== action.id
            );
        default:
            return state;
    }
}
