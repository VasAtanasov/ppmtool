import React from 'react';
import ProjectTask from './ProjectTask/ProjectTask';

const Backlog = ({ projectTasks }) => (
    <div className="row">
        <div className="col-md-4">
            <div className="card text-center mb-2">
                <div className="card-header bg-secondary text-white">
                    <h3>TO DO</h3>
                </div>
            </div>
            {projectTasks.map(task => (
                <ProjectTask projectTask={task} />
            ))}
        </div>
        <div className="col-md-4">
            <div className="card text-center mb-2">
                <div className="card-header bg-primary text-white">
                    <h3>In Progress</h3>
                </div>
            </div>
            {
                //  <!-- SAMPLE PROJECT TASK STARTS HERE -->
                //         <!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
        </div>
        <div className="col-md-4">
            <div className="card text-center mb-2">
                <div className="card-header bg-success text-white">
                    <h3>Done</h3>
                </div>
            </div>
            {
                // <!-- SAMPLE PROJECT TASK STARTS HERE -->
                // <!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
        </div>
    </div>
);

export default Backlog;
