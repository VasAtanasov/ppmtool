import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';

const rootReducer = combineReducers({
    errors: errorReducer,
    project: projectReducer
});

export default rootReducer;
