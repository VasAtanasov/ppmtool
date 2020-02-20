import React from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';

const TextInput = ({ name, label, onChange, placeholder, value, error }) => {
    let wrapperClass = 'form-group';
    if (error && error.length > 0) {
        wrapperClass += ' has-error';
    }

    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <input
                type="text"
                name={name}
                className={classnames('form-control form-control-lg', {
                    'is-invalid': error
                })}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            />
            {error && <div className="invalid-feedback">{error}</div>}
        </div>
    );
};

TextInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string
};

export default TextInput;
