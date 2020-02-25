import { SET_CURRENT_USER } from '../actions/actionTypes';

const initialState = {
    validToken: false,
    user: {}
};

const booleanActionPayload = payload => {
    if (payload) {
        return true;
    } else {
        return false;
    }
};

export default function(state = initialState, action) {
    switch (action.type) {
        case SET_CURRENT_USER:
            return {
                ...state,
                validToken: booleanActionPayload(action.registeredUser),
                user: action.registeredUser
            };

        default:
            return state;
    }
}
