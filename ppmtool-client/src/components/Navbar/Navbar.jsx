import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Container from 'react-bootstrap/Container';
import { NavLink } from 'react-router-dom';
import { StyledLink, NavbarWarper } from './Navbar.styles';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { logout } from '../../actions/securityActions';

const Navigation = ({ security, logout }) => {
    const { validToken, user } = security;

    const handleLogout = () => logout();

    return (
        <NavbarWarper>
            <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
                <Container>
                    <Navbar.Brand>
                        <StyledLink>
                            <NavLink className="nav-link" to="/">
                                Personal Kanban Tool
                            </NavLink>
                        </StyledLink>
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    {validToken && user ? (
                        <NavLink id="responsive-navbar-nav">
                            <Nav className="mr-auto">
                                <StyledLink>
                                    <NavLink
                                        className="nav-link"
                                        to="/dashboard"
                                    >
                                        Dashboard
                                    </NavLink>
                                </StyledLink>
                            </Nav>
                            <Nav>
                                <StyledLink>
                                    <NavLink
                                        className="nav-link"
                                        to="/dashboard"
                                    >
                                        <i className="fas fa-user-circle mr-1" />
                                        {user.fullName}
                                    </NavLink>
                                </StyledLink>
                                <StyledLink>
                                    <NavLink
                                        className="nav-link"
                                        to="/login"
                                        onClick={handleLogout}
                                    >
                                        Logout
                                    </NavLink>
                                </StyledLink>
                            </Nav>
                        </NavLink>
                    ) : (
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="ml-auto">
                                <StyledLink>
                                    <NavLink
                                        className="nav-link"
                                        to="/register"
                                    >
                                        Sign Up
                                    </NavLink>
                                </StyledLink>
                                <StyledLink>
                                    <NavLink className="nav-link" to="/login">
                                        Login
                                    </NavLink>
                                </StyledLink>
                            </Nav>
                        </Navbar.Collapse>
                    )}
                </Container>
            </Navbar>
        </NavbarWarper>
    );
};

Navigation.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security
});

export default connect(mapStateToProps, { logout })(Navigation);
