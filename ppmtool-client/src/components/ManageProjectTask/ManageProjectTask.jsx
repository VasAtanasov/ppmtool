import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { toast } from 'react-toastify';
import { connect } from 'react-redux';
import { createProjectTask, loadBacklog } from '../../actions/backlogActions';
import ProjectTaskForm from './ProjectTaskForm';

const newProjectTask = {
    id: '',
    summary: '',
    acceptanceCriteria: '',
    projectSequence: '',
    status: '',
    priority: 0,
    dueDate: '',
    projectIdentifier: '',
    errors: {}
};

const ManageProjectTask = ({
    createProjectTask,
    projectTasks,
    loadBacklog,
    history,
    ...props
}) => {
    const { projectIdentifier } = props.match.params;

    const [projectTask, setProjectTask] = useState({
        ...props.projectTask,
        projectIdentifier
    });
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        if (projectTasks.length === 0) {
            loadBacklog(projectIdentifier).catch(error => {
                alert('Loading projects tasks failed' + error);
            });
        } else {
            setProjectTask({ ...props.projectTask });
        }

        // eslint-disable-next-line
    }, [props.projectTask]);

    const handleChange = event => {
        const { name, value } = event.target;
        setProjectTask(prevProjectTask => ({
            ...prevProjectTask,
            [name]: value
        }));
    };

    const formIsValid = () => {
        const { summary } = projectTask;
        const errors = {};

        if (!summary) errors.summary = 'Summery name is required.';

        setErrors(errors);
        // Form is valid if the errors object still has no properties
        return Object.keys(errors).length === 0;
    };

    const handleSave = event => {
        event.preventDefault();
        if (!formIsValid()) return;
        debugger;
        setSaving(true);
        createProjectTask(projectIdentifier, projectTask)
            .then(() => {
                toast.success('Project task saved.');
                history.push(`/project-board/${projectIdentifier}`);
            })
            .catch(error => {
                debugger;
                setSaving(false);
                setErrors({ ...error.response.data });
            });
    };
    return (
        <ProjectTaskForm
            projectIdentifier={projectIdentifier}
            projectTask={projectTask}
            errors={errors}
            onChange={handleChange}
            onSave={handleSave}
            saving={saving}
        />
    );
};

ManageProjectTask.propTypes = {
    createProjectTask: PropTypes.func.isRequired
};

export function getProjectById(projectTasks, taskSequence) {
    return (
        projectTasks.find(
            projectTask => projectTask.projectSequence === taskSequence
        ) || null
    );
}
const mapStateToProps = ({ backlog }, ownProps) => {
    const taskSequence = ownProps.match.params.taskSequence;

    const projectTask =
        taskSequence && backlog.length > 0
            ? getProjectById(backlog, taskSequence)
            : newProjectTask;

    return {
        projectTasks: backlog,
        projectTask
    };
};

export default connect(mapStateToProps, { createProjectTask, loadBacklog })(
    ManageProjectTask
);
