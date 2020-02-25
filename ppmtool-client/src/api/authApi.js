import http from '../utils/http';
import { handleResponse, handleError } from './apiUtils';

const AUTH_URL = '/api/users';

export const registerUser = async registerRequest => {
    try {
        const res = await http.post(`${AUTH_URL}/register`, {
            data: registerRequest
        });
        return handleResponse(res);
    } catch (error) {
        return handleError(error);
    }
};

export const login = async loginRequest => {
    try {
        const res = await http.post(`${AUTH_URL}/login`, {
            data: loginRequest
        });
        return handleResponse(res);
    } catch (error) {
        return handleError(error);
    }
};
