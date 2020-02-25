import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import PasswordInput from '../common/PasswordInput';

const RegisterForm = ({
    registerRequest,
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
                value={registerRequest.username}
                error={errors.username}
            />

            <TextInput
                name="fullName"
                label="Full Name"
                placeholder="Full Name"
                onChange={onChange}
                value={registerRequest.fullName}
                error={errors.fullName}
            />

            <PasswordInput
                name="password"
                label="Password"
                placeholder="Password"
                onChange={onChange}
                value={registerRequest.password}
                error={errors.password}
            />

            <PasswordInput
                name="confirmPassword"
                label="Confirm Password"
                placeholder="Confirm Password"
                onChange={onChange}
                value={registerRequest.confirmPassword}
                error={errors.confirmPassword}
            />

            <button
                type="submit"
                disabled={saving}
                className="btn btn-primary btn-block mt-4"
            >
                {saving ? 'Registering...' : 'Register'}
            </button>
        </form>
    );
};

RegisterForm.propTypes = {
    registerRequest: PropTypes.object.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool
};

export default RegisterForm;
