import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import DatePicker from '../common/DatePicker';

const ProjectForm = ({
    project,
    onSave,
    onChange,
    saving = false,
    errors = {}
}) => {
    return (
        <form onSubmit={onSave}>
            <h2>{project.id ? 'Update' : 'Create'} Project form</h2>
            {errors.onSave && (
                <div className="alert alert-danger" role="alert">
                    {errors.onSave}
                </div>
            )}
            <TextInput
                name="projectName"
                label="Project Name"
                onChange={onChange}
                placeholder="Project Name"
                value={project.projectName}
                error={errors.projectName}
            />

            <TextInput
                name="projectIdentifier"
                label="Project Identifier"
                placeholder="Project Identifier"
                onChange={onChange}
                value={project.projectIdentifier}
                error={errors.projectIdentifier}
            />

            <TextInput
                name="description"
                label="Description"
                placeholder="Description"
                onChange={onChange}
                value={project.description}
                error={errors.description}
            />

            <DatePicker
                name="startDate"
                label="Start Date"
                onChange={onChange}
                value={project.startDate}
            />

            <DatePicker
                name="endDate"
                label="End Date"
                onChange={onChange}
                value={project.startDate}
            />

            <button
                type="submit"
                disabled={saving}
                className="btn btn-primary btn-block mt-4"
            >
                {saving ? 'Saving...' : 'Save'}
            </button>
        </form>
    );
};

ProjectForm.propTypes = {
    project: PropTypes.object.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool
};

export default ProjectForm;
