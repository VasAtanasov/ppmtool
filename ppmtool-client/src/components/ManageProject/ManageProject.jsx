import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { toast } from 'react-toastify';
import ProjectForm from './ProjectForm';
import { connect } from 'react-redux';
import { createProject, getProjects } from '../../actions/projectActions';

const newProject = {
    id: null,
    projectName: '',
    description: '',
    startDate: '',
    endDate: ''
};

const ManageProject = ({
    projects,
    getProjects,
    createProject,
    history,
    ...props
}) => {
    const [project, setProject] = useState({ ...props.project });
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        if (projects.length === 0) {
            getProjects().catch(error => {
                alert('Loading projects failed' + error);
            });
        } else {
            setProject({ ...props.project });
        }

        // eslint-disable-next-line
    }, [props.project]);

    const handleChange = event => {
        const { name, value } = event.target;
        setProject(prevProject => ({
            ...prevProject,
            [name]: value
        }));
    };

    const formIsValid = () => {
        const { projectName, description } = project;
        const errors = {};

        if (!projectName) errors.projectName = 'Project name is required.';
        if (!description) errors.description = 'Description is required';

        setErrors(errors);
        // Form is valid if the errors object still has no properties
        return Object.keys(errors).length === 0;
    };

    const handleSave = event => {
        event.preventDefault();
        if (!formIsValid()) return;

        setSaving(true);
        createProject(project)
            .then(() => {
                toast.success('Project saved.');
                history.push('/dashboard');
            })
            .catch(error => {
                setSaving(false);
                setErrors({ ...error.response.data });
            });
    };

    return (
        <ProjectForm
            project={project}
            errors={errors}
            onChange={handleChange}
            onSave={handleSave}
            saving={saving}
        />
    );
};

export function getProjectById(projects, id) {
    return projects.find(project => project.id === id) || null;
}

ManageProject.propTypes = {
    project: PropTypes.object.isRequired,
    projects: PropTypes.array.isRequired,
    getProjects: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    history: PropTypes.object.isRequired
};

const mapStateToProps = ({ projects }, ownProps) => {
    const id = ownProps.match.params.id;
    const project =
        id && projects.length > 0 ? getProjectById(projects, id) : newProject;
    return {
        projects,
        project: project
    };
};

const mapDispatchToProps = { createProject, getProjects };

export default connect(mapStateToProps, mapDispatchToProps)(ManageProject);
