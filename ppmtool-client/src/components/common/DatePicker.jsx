import React from 'react';
import PropTypes from 'prop-types';

const DatePicker = ({ name, label, onChange, value }) => {
    let wrapperClass = 'form-group';

    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <input
                type="date"
                className="form-control form-control-lg"
                name={name}
                value={value}
                onChange={onChange}
            />
        </div>
    );
};

DatePicker.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    value: PropTypes.string
};

export default DatePicker;
