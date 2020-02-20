import React from 'react';
import { LandingContainer } from './Landing.styles';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const Landing = () => (
    <LandingContainer>
        <Container>
            <Row>
                <Col md={12} className="text-center">
                    <h1 className="display-3 mb-4">Personal Kanban Tool</h1>
                    <p className="lead">
                        Create your account to join active projects or start you
                        own
                    </p>
                    <hr />
                    <a
                        href="register.html"
                        className="btn btn-lg btn-primary mr-2"
                    >
                        Sign Up
                    </a>
                    <a
                        href="login.html"
                        className="btn btn-lg btn-secondary mr-2"
                    >
                        Login
                    </a>
                </Col>
            </Row>
        </Container>
    </LandingContainer>
);

export default Landing;
