import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { getProjects } from '../../actions/projectActions';
import PropTypes from 'prop-types';
import ProjectItem from './ProjectItem';

const Dashboard = ({ project, getProjects }) => {
    useEffect(() => {
        getProjects();
    }, []);

    const { projects } = project;

    return (
        <div className="row">
            <div className="col-md-12">
                <h1 className="display-4 text-center">Projects</h1>
                <br />
                <a href="ProjectForm.html" className="btn btn-lg btn-info">
                    Create a Project
                </a>
                <br />
                <hr />
                {projects.map(project => (
                    <ProjectItem key={project.id} project={project} />
                ))}
            </div>
        </div>
    );
};

Dashboard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    project: state.project
});

const mapDispatchToProps = {
    getProjects
};

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
