import React from 'react';
import PropTypes from 'prop-types';

const SelectInput = ({
    name,
    label,
    onChange,
    defaultOption,
    defaultValue,
    value,
    options
}) => {
    return (
        <div className="form-group">
            <label htmlFor={name}>{label}</label>
            <div className="field">
                {/* Note, value is set here rather than on the option - docs: https://facebook.github.io/react/docs/forms.html */}
                <select
                    className="form-control form-control-lg"
                    name={name}
                    value={value}
                    onChange={onChange}
                >
                    <option value={defaultValue}>{defaultOption}</option>
                    {options.map(option => {
                        return (
                            <option key={option.value} value={option.value}>
                                {option.text}
                            </option>
                        );
                    })}
                </select>
            </div>
        </div>
    );
};

SelectInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    defaultOption: PropTypes.string,
    defaultValue: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
    options: PropTypes.arrayOf(PropTypes.object)
};

export default SelectInput;
