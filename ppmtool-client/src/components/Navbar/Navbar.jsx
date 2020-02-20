import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Container from 'react-bootstrap/Container';
import { NavLink } from 'react-router-dom';
import { StyledLink } from './Navbar.styles';

const Navigation = () => (
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
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                    <StyledLink>
                        <NavLink className="nav-link" to="/dashboard">
                            Dashboard
                        </NavLink>
                    </StyledLink>
                </Nav>
                <Nav>
                    <StyledLink>
                        <NavLink className="nav-link" to="/signup">
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
        </Container>
    </Navbar>
);

export default Navigation;
