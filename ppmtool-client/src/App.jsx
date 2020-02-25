import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import {
    Navbar,
    Dashboard,
    Landing,
    ManageProject,
    ProjectBoard,
    ManageProjectTask,
    Register,
    Login,
    SecuredRoute
} from './components';
import Theme from './Theme';
import GlobalStyles from './utils/globalStyle';
import Container from 'react-bootstrap/Container';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import jwt_decode from 'jwt-decode';
import setJWToken from './utils/setJWToken';
import { SET_CURRENT_USER } from './actions/actionTypes';
import { logout } from './actions/securityActions';
import store from './store';

export const routes = [
    {
        path: '/home',
        component: Landing,
        name: 'Landing',
        isSecured: false
    },
    {
        path: '/register',
        component: Register,
        name: 'Register',
        isSecured: false
    },
    {
        path: '/login',
        component: Login,
        name: 'Login',
        isSecured: false
    },
    {
        path: '/register',
        component: Register,
        name: 'Register',
        isSecured: false
    },
    {
        path: '/dashboard',
        component: Dashboard,
        name: 'Dashboard',
        isSecured: true
    },
    {
        path: '/project/:id',
        component: ManageProject,
        name: 'ManageProject',
        isSecured: true
    },
    {
        path: '/project',
        component: ManageProject,
        name: 'ManageProject',
        isSecured: true
    },
    {
        path: '/project-board/:projectIdentifier',
        component: ProjectBoard,
        name: 'ProjectBoard',
        isSecured: true
    },
    {
        path: '/project-task/:projectIdentifier/:taskSequence',
        component: ManageProjectTask,
        name: 'ManageProjectTask',
        isSecured: true
    },
    {
        path: '/project-task/:projectIdentifier',
        component: ManageProjectTask,
        name: 'ManageProjectTask',
        isSecured: true
    }
];

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
    setJWToken(jwtToken);
    const decoded_jwtToken = jwt_decode(jwtToken);
    store.dispatch({
        type: SET_CURRENT_USER,
        registeredUser: decoded_jwtToken
    });

    const currentTime = Date.now() / 1000;
    if (decoded_jwtToken.exp < currentTime) {
        store.dispatch(logout());
        window.location.href = '/';
    }
}

function App() {
    return (
        <Theme>
            <GlobalStyles />
            <Navbar className="mb-4" />
            <Container style={{ paddingTop: '80px' }}>
                <Switch>
                    <Redirect exact from="/" to="/home" />
                    {routes.map((routObj, idx) =>
                        routObj.isSecured ? (
                            <SecuredRoute
                                exact
                                key={idx}
                                path={routObj.path}
                                component={routObj.component}
                            />
                        ) : (
                            <Route
                                exact
                                key={idx}
                                path={routObj.path}
                                component={routObj.component}
                            />
                        )
                    )}
                </Switch>
                <ToastContainer autoClose={3000} />
            </Container>
        </Theme>
    );
}

export default App;
