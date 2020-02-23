import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import Backlog from './Backlog';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { loadBacklog } from '../../actions/backlogActions';
import { toast } from 'react-toastify';

const ProjectBoard = ({ backlog, loadBacklog, history, ...props }) => {
    const { projectIdentifier } = props.match.params;

    useEffect(() => {
        loadBacklog(projectIdentifier).catch(error => {
            toast.error(error.response.data.projectIdentifier);
            history.push('/dashboard');
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <div className="container">
            <Link
                to={`/project-task/${projectIdentifier}`}
                className="btn btn-primary mb-3"
            >
                <i className="fas fa-plus-circle"> Create Project Task</i>
            </Link>
            <br />
            <hr />
            {backlog.length === 0 ? (
                <div className="alert alert-info text-center" role="alert">
                    No Project Tasks on this board
                </div>
            ) : (
                <Backlog projectTasks={backlog} />
            )}
        </div>
    );
};

ProjectBoard.propTypes = {
    backlog: PropTypes.array.isRequired,
    loadBacklog: PropTypes.func.isRequired
};

const mapStateToProps = ({ backlog }) => ({
    backlog
});

export default connect(mapStateToProps, { loadBacklog })(ProjectBoard);
