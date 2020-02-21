import React, { Fragment, useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { getProjects, deleteProject } from '../../actions/projectActions';
import PropTypes from 'prop-types';
import { Redirect } from 'react-router-dom';
import ItemsList from './ItemsList';
import { toast } from 'react-toastify';

const Dashboard = ({ projects, getProjects, deleteProject }) => {
    const [redirectToAddCoursePage, setRedirectToAddCoursePage] = useState(
        false
    );

    useEffect(() => {
        if (projects.length === 0) {
            getProjects().catch(error => {
                alert('Loading courses failed' + error);
            });
        }
        // eslint-disable-next-line
    }, []);

    const handleDelete = async id => {
        toast.success('Course deleted');
        try {
            await deleteProject(id);
        } catch (error) {
            toast.error('Delete failed. ' + error.message, {
                autoClose: false
            });
        }
    };

    return (
        <Fragment>
            {redirectToAddCoursePage && <Redirect to="/project" />}
            <div className="row">
                <div className="col-md-12">
                    <h1 className="display-4 text-center">Projects</h1>
                    <br />
                    <button
                        style={{ marginBottom: 20 }}
                        className="btn btn-lg btn-info"
                        onClick={() => setRedirectToAddCoursePage(true)}
                    >
                        Create a Project
                    </button>
                    <br />
                    <hr />
                    <ItemsList
                        projects={projects}
                        handleDelete={handleDelete}
                    />
                </div>
            </div>
        </Fragment>
    );
};

Dashboard.propTypes = {
    projects: PropTypes.array.isRequired,
    getProjects: PropTypes.func.isRequired,
    deleteProject: PropTypes.func.isRequired
};

const mapStateToProps = ({ projects }) => ({
    projects
});

const mapDispatchToProps = {
    getProjects,
    deleteProject
};

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
