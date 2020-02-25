import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import PasswordInput from '../common/PasswordInput';

const LoginForm = ({
    loginRequest,
    onSave,
    onChange,
    saving = false,
    errors = {}
}) => {
    return (
        <form onSubmit={onSave}>
            <TextInput
                name="username"
                label="Username"
                onChange={onChange}
                placeholder="Email"
                value={loginRequest.username}
                error={errors.username}
            />

            <PasswordInput
                name="password"
                label="Password"
                placeholder="Password"
                onChange={onChange}
                value={loginRequest.password}
                error={errors.password}
            />

            <button
                type="submit"
                disabled={saving}
                className="btn btn-primary btn-block mt-4"
            >
                {saving ? 'Processing...' : 'Login'}
            </button>
        </form>
    );
};

LoginForm.propTypes = {
    loginRequest: PropTypes.object.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool
};

export default LoginForm;
