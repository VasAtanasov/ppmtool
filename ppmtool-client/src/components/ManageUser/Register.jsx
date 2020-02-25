import React, { useState } from 'react';
import { createNewUser } from '../../actions/securityActions';
import PropTypes from 'prop-types';
import { toast } from 'react-toastify';
import { connect } from 'react-redux';
import RegisterForm from './RegisterForm';

const registerRequest = {
    username: '',
    fullName: '',
    password: '',
    confirmPassword: ''
};

const Register = ({ security, history, createNewUser, ...props }) => {
    if (security.validToken) {
        history.push('/dashboard');
    }

    const [registerRequest, setRegisterRequest] = useState({
        ...props.registerRequest
    });
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    const handleChange = event => {
        const { name, value } = event.target;
        setRegisterRequest(registerRequest => ({
            ...registerRequest,
            [name]: value
        }));
    };

    const formIsValid = () => {
        const {
            username,
            fullName,
            password,
            confirmPassword
        } = registerRequest;

        const errors = {};

        if (!username) errors.username = 'Username is required.';

        const EMAIL_REGEX = RegExp('[^@ ]+@[^@ ]+\\.[^@ ]+');
        if (!EMAIL_REGEX.test(username)) {
            errors.username = 'Email is invalid.';
        }

        if (!fullName) errors.fullName = 'Full name is required.';
        if (!password) errors.password = 'Password name is required.';
        if (!confirmPassword)
            errors.confirmPassword = 'Confirm Password name is required.';

        if (password !== confirmPassword) {
            errors.password = 'Passwords do not match.';
            errors.confirmPassword = 'Passwords do not match.';
        }

        setErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleRegister = event => {
        event.preventDefault();
        if (!formIsValid()) return;
        setSaving(true);
        createNewUser(registerRequest)
            .then(() => {
                toast.success('Registration successful.');
                history.push('/login');
            })
            .catch(error => {
                setSaving(false);
                setErrors({ ...error.response.data });
            });
    };
    return (
        <div className="register">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h1 className="display-4 text-center">Sign Up</h1>
                        <p className="lead text-center">Create your Account</p>
                        <RegisterForm
                            registerRequest={registerRequest}
                            errors={errors}
                            onChange={handleChange}
                            onSave={handleRegister}
                            saving={saving}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

Register.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
    registerRequest: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    registerRequest,
    security: state.security
});

export default connect(mapStateToProps, { createNewUser })(Register);
