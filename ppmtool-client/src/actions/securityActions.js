import * as types from './actionTypes';
import * as authApi from '../api/authApi';
import setJWToken from '../utils/setJWToken';
import jwt_decode from 'jwt-decode';

export const loginSuccess = registeredUser => {
    return { type: types.SET_CURRENT_USER, registeredUser };
};

export const logoutSuccess = () => {
    return { type: types.SET_CURRENT_USER, registeredUser: {} };
};

export const createNewUser = registerRequest => async () => {
    try {
        await authApi.registerUser(registerRequest);
    } catch (error) {
        throw error;
    }
};

export const loginUser = LoginRequest => async dispatch => {
    try {
        const { token } = await authApi.login(LoginRequest);
        localStorage.setItem('jwtToken', token);
        setJWToken(token);
        const decoded = jwt_decode(token);
        dispatch(loginSuccess(decoded));
    } catch (error) {
        throw error;
    }
};

export const logout = () => dispatch => {
    localStorage.removeItem('jwtToken');
    setJWToken(false);
    dispatch(logoutSuccess());
};
