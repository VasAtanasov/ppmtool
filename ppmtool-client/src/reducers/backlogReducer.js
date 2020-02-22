import * as types from '../actions/actionTypes';
import initialState from './initialState';

export default function(state = initialState.projectTasks, action) {
    switch (action.type) {
        case types.GET_BACKLOG:
            return action.backlog;

        case types.DELETE_PROJECT_TASK:
            return state.filter(
                projectTask =>
                    projectTask.projectSequence !== action.taskSequence
            );
        default:
            return state;
    }
}
