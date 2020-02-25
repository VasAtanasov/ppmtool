import React from 'react';
import { LandingContainer } from './Landing.styles';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

const Landing = ({ security, history }) => {
    if (security.validToken) {
        history.push('/dashboard');
    }

    return (
        <LandingContainer>
            <Container>
                <Row>
                    <Col md={12} className="text-center">
                        <h1 className="display-3 mb-4">Personal Kanban Tool</h1>
                        <p className="lead">
                            Create your account to join active projects or start
                            you own
                        </p>
                        <hr />
                        <Link
                            className="btn btn-lg btn-primary mr-2"
                            to="/register"
                        >
                            Sign Up
                        </Link>
                        <Link
                            className="btn btn-lg btn-secondary mr-2"
                            to="/login"
                        >
                            Login
                        </Link>
                    </Col>
                </Row>
            </Container>
        </LandingContainer>
    );
};

Landing.propTypes = {
    security: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security
});

export default connect(mapStateToProps)(Landing);
