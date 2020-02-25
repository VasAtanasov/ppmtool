import React, { useState } from 'react';
import { loginUser } from '../../actions/securityActions';
import PropTypes from 'prop-types';
import { toast } from 'react-toastify';
import { connect } from 'react-redux';
import LoginForm from './LoginForm';

const loginRequest = {
    username: '',
    password: ''
};

const Login = ({ security, history, loginUser, ...props }) => {
    if (security.validToken) {
        history.push('/dashboard');
    }

    const [loginRequest, setLoginRequest] = useState({
        ...props.loginRequest
    });
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    const handleChange = event => {
        const { name, value } = event.target;
        setLoginRequest(loginRequest => ({
            ...loginRequest,
            [name]: value
        }));
    };

    const formIsValid = () => {
        const { username, password } = loginRequest;

        const errors = {};

        if (!username) errors.username = 'Username is required.';

        const EMAIL_REGEX = RegExp('[^@ ]+@[^@ ]+\\.[^@ ]+');
        if (!EMAIL_REGEX.test(username)) {
            errors.username = 'Email is invalid.';
        }

        if (!password) errors.password = 'Password name is required.';

        setErrors(errors);
        return Object.keys(errors).length === 0;
    };

    const handleLogin = event => {
        event.preventDefault();
        if (!formIsValid()) return;
        setSaving(true);
        loginUser(loginRequest)
            .then(() => {
                toast.success('Login successful.');
                history.push('/dashboard');
            })
            .catch(error => {
                setSaving(false);
                setErrors({ ...error.response.data });
            });
    };
    return (
        <div className="login">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h1 className="display-4 text-center">Log In</h1>
                        <LoginForm
                            loginRequest={loginRequest}
                            errors={errors}
                            onChange={handleChange}
                            onSave={handleLogin}
                            saving={saving}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

Login.propTypes = {
    loginUser: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
    loginRequest: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    loginRequest,
    security: state.security
});

export default connect(mapStateToProps, { loginUser })(Login);
