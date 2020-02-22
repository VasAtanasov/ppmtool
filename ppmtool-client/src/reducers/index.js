import { combineReducers } from 'redux';
import projects from './projectReducer';
import backlog from './backlogReducer';

const rootReducer = combineReducers({
    projects,
    backlog
});

export default rootReducer;
