import React from 'react';
import PropTypes from 'prop-types';
import TextInput from '../common/TextInput';
import DatePicker from '../common/DatePicker';
import SelectInput from '../common/SelectInput';
import { Link } from 'react-router-dom';

const ProjectTaskForm = ({
    projectTask,
    onSave,
    onChange,
    saving = false,
    errors = {},
    projectIdentifier
}) => {
    return (
        <div className="add-PBI">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <Link
                            to={`/project-board/${projectIdentifier}`}
                            className="btn btn-light"
                        >
                            Back to Project Board
                        </Link>
                        <h4 className="display-4 text-center">
                            {projectTask.projectSequence ? 'Update' : 'Add'}{' '}
                            Project Task
                        </h4>
                        {projectTask.projectSequence && (
                            <p className="lead text-center">
                                {projectTask.projectSequence}
                            </p>
                        )}
                        <form onSubmit={onSave}>
                            <TextInput
                                name="summary"
                                label="Project Task summary"
                                onChange={onChange}
                                placeholder="Project Task summary"
                                value={projectTask.summary}
                                error={errors.summary}
                            />

                            <div className="form-group">
                                <label htmlFor="Acceptance Criteria">
                                    Acceptance Criteria
                                </label>
                                <textarea
                                    className="form-control form-control-lg"
                                    name="acceptanceCriteria"
                                    placeholder="Acceptance Criteria"
                                    onChange={onChange}
                                    value={projectTask.acceptanceCriteria}
                                    error={errors.acceptanceCriteria}
                                />
                            </div>

                            <DatePicker
                                name="dueDate"
                                label="Due Date"
                                onChange={onChange}
                                value={projectTask.dueDate}
                            />

                            <SelectInput
                                name="priority"
                                label="Priority"
                                value={projectTask.priority || ''}
                                defaultOption="Select Priority"
                                defaultValue={0}
                                options={['High', 'Medium', 'Low'].map(
                                    (text, idx) => ({
                                        value: idx + 1,
                                        text
                                    })
                                )}
                                onChange={onChange}
                            />

                            <SelectInput
                                name="status"
                                label="Status"
                                value={projectTask.status || ''}
                                defaultOption="Select Status"
                                defaultValue=""
                                options={['TO_DO', 'IN_PROGRESS', 'DONE'].map(
                                    value => ({
                                        value,
                                        text: value
                                    })
                                )}
                                onChange={onChange}
                            />

                            <button
                                type="submit"
                                disabled={saving}
                                className="btn btn-primary btn-block mt-4"
                            >
                                {saving ? 'Saving...' : 'Save'}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

ProjectTaskForm.propTypes = {
    projectTask: PropTypes.object.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    saving: PropTypes.bool
};

export default ProjectTaskForm;
