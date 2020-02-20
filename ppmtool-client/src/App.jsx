import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import { Navbar, Dashboard, Landing } from './components';
import Theme from './Theme';
import GlobalStyles from './utils/globalStyle';
import Container from 'react-bootstrap/Container';

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
    }
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
            </Container>
        </Theme>
    );
}

export default App;
