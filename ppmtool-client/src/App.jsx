import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import {
    Navbar,
    Dashboard,
    Landing,
    ManageProject,
    ProjectBoard
} from './components';
import Theme from './Theme';
import GlobalStyles from './utils/globalStyle';
import Container from 'react-bootstrap/Container';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export const routes = [
    {
        path: '/home',
        component: Landing,
        name: 'Landing'
    },
    {
        path: '/dashboard',
        component: Dashboard,
        name: 'Dashboard'
    },
    {
        path: '/project/:id',
        component: ManageProject,
        name: 'ManageProject'
    },
    {
        path: '/project',
        component: ManageProject,
        name: 'ManageProject'
    },
    {
        path: '/project-board/:id',
        component: ProjectBoard,
        name: 'ProjectBoard'
    }
    // {
    //     path: '/project-task/:id',
    //     component: ProjectBoard,
    //     name: 'ProjectBoard'
    // }
];

function App() {
    return (
        <Theme>
            <GlobalStyles />
            <Navbar className="mb-4" />
            <Container style={{ paddingTop: '80px' }}>
                <Switch>
                    <Redirect exact from="/" to="/home" />
                    {routes.map((routObj, idx) => (
                        <Route
                            exact
                            key={idx}
                            path={routObj.path}
                            component={routObj.component}
                        />
                    ))}
                </Switch>
                <ToastContainer autoClose={3000} />
            </Container>
        </Theme>
    );
}

export default App;
