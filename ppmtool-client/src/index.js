import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import configureStore from './store';
import { Provider as ReduxProvider } from 'react-redux';
import jwt_decode from 'jwt-decode';
import setJWToken from './utils/setJWToken';
import { SET_CURRENT_USER } from './actions/actionTypes';
import { logout } from './actions/securityActions';

const store = configureStore();

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
    setJWToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({
        type: SET_CURRENT_USER,
        payload: decoded_jwtToken
    });

    const currentTime = Date.now() / 1000;
    if (decoded_jwtToken.exp < currentTime) {
        store.dispatch(logout());
        window.location.href = '/';
    }
}

render(
    <ReduxProvider store={store}>
        <Router>
            <App />
        </Router>
    </ReduxProvider>,
    document.getElementById('root')
);
