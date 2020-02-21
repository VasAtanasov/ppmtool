import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

const ItemsList = ({ projects, handleDelete }) =>
    projects.map(project => (
        <div
            key={project.projectIdentifier}
            className="card card-body bg-light mb-3"
        >
            <div className="row">
                <div className="col-2">
                    <span className="mx-auto">{project.projectIdentifier}</span>
                </div>
                <div className="col-lg-6 col-md-4 col-8">
                    <h3>{project.projectName}</h3>
                    <p>{project.description}</p>
                </div>
                <div className="col-md-4 d-none d-lg-block">
                    <ul className="list-group">
                        {/* <a href="#"> */}
                        <li className="list-group-item board">
                            <i className="fa fa-flag-checkered pr-1">
                                {' '}
                                Project Board{' '}
                            </i>
                        </li>
                        {/* </a> */}
                        <Link to={`/project/${project.id}`}>
                            <li className="list-group-item update">
                                <i className="fa fa-edit pr-1">
                                    {' '}
                                    Update Project Info
                                </i>
                            </li>
                        </Link>
                        <li
                            className="list-group-item delete"
                            onClick={() =>
                                handleDelete(project.projectIdentifier)
                            }
                        >
                            <i className="fa fa-minus-circle pr-1">
                                {' '}
                                Delete Project
                            </i>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    ));

ItemsList.propTypes = {
    projects: PropTypes.array.isRequired,
    handleDelete: PropTypes.func.isRequired
};

export default ItemsList;
