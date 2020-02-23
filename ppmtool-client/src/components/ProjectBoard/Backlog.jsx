import React from 'react';
import ProjectTask from './ProjectTask/ProjectTask';

const Backlog = ({ projectTasks }) => {
    let todoItems = [];
    let inProgressItems = [];
    let doneItems = [];

    for (let i = 0; i < projectTasks.length; i++) {
        const taskObj = projectTasks[i];

        let task = <ProjectTask key={taskObj.id} projectTask={taskObj} />;

        if (task.props.projectTask.status === 'TO_DO') {
            todoItems.push(task);
        }

        if (task.props.projectTask.status === 'IN_PROGRESS') {
            inProgressItems.push(task);
        }

        if (task.props.projectTask.status === 'DONE') {
            doneItems.push(task);
        }
    }

    return (
        <div className="row">
            <div className="col-md-4">
                <div className="card text-center mb-2">
                    <div className="card-header bg-secondary text-white">
                        <h3>TO DO</h3>
                    </div>
                </div>
                {todoItems}
            </div>
            <div className="col-md-4">
                <div className="card text-center mb-2">
                    <div className="card-header bg-primary text-white">
                        <h3>In Progress</h3>
                    </div>
                </div>
                {inProgressItems}
            </div>
            <div className="col-md-4">
                <div className="card text-center mb-2">
                    <div className="card-header bg-success text-white">
                        <h3>Done</h3>
                    </div>
                </div>
                {doneItems}
            </div>
        </div>
    );
};

export default Backlog;
