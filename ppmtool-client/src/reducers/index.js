import { combineReducers } from 'redux';
import projects from './projectReducer';
import backlog from './backlogReducer';
import security from './securityReducer';

const rootReducer = combineReducers({
    projects,
    backlog,
    security
});

export default rootReducer;
