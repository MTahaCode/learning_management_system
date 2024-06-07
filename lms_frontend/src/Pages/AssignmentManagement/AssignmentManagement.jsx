import React, { useEffect, useState } from "react";
import "../../css/AssignmentManagement.css";

const AssignmentManagement = ({ loginCredentials, setLoginCredentials }) => {
  const [teacherAssignments, setTeacherAssignments] = useState([]);
  const [assignmentList, setAssignmentList] = useState([]);
  const [selectedTeacherAssignment, setSelectedTeacherAssignment] =
    useState(null);
  const [newAssignment, setNewAssignment] = useState({
    title: "",
    description: "",
    totalMarks: 0,
    weightage: 0,
  });

  useEffect(() => {
    fetchTeacherAssignments();
  }, []);

  const fetchTeacherAssignments = () => {
    fetch(`/teacherAssignment/teacher/${loginCredentials.loginId}`)
      .then((res) => res.json())
      .then((data) => {
        setTeacherAssignments(data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const fetchAssignmentsByTeacherAssignment = (teacherAssignmentId) => {
    fetch(`/assignments/teacherAssignment/${teacherAssignmentId}`)
      .then((res) => res.json())
      .then((data) => {
        setAssignmentList(data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleTeacherAssignmentSelection = (teacherAssignmentId) => {
    setSelectedTeacherAssignment(teacherAssignmentId);
    fetchAssignmentsByTeacherAssignment(teacherAssignmentId);
  };

  const handleNewAssignmentChange = (event) => {
    const { name, value } = event.target;
    setNewAssignment((prev) => ({ ...prev, [name]: value }));
  };

  const handleAssignmentSubmission = () => {
    const assignmentData = {
      ...newAssignment,
      teacherAssignmentEntity: {
        id: selectedTeacherAssignment,
      },
    };

    fetch(`/assignment`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(assignmentData),
    })
      .then((res) => res.json())
      .then((data) => {
        setNewAssignment({
          title: "",
          description: "",
          totalMarks: 0,
          weightage: 0,
        });
        fetchAssignmentsByTeacherAssignment(selectedTeacherAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleDeleteAssignment = (assignmentId) => {
    fetch(`/assignment/${assignmentId}`, {
      method: "DELETE",
    })
      .then(() => {
        fetchAssignmentsByTeacherAssignment(selectedTeacherAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleUpdateAssignment = (assignmentId, updatedAssignment) => {
    fetch(`/assignment/${assignmentId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedAssignment),
    })
      .then((res) => res.json())
      .then(() => {
        fetchAssignmentsByTeacherAssignment(selectedTeacherAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  return (
    <div className="assignment-container">
      <h1>Manage Assignments</h1>
      <h2>Select a Course/Section</h2>
      <ul className="teacher-assignment-list">
        {teacherAssignments.length > 0 &&
          teacherAssignments.map((ta) => (
            <li key={ta.id}>
              <button onClick={() => handleTeacherAssignmentSelection(ta.id)}>
                {ta.courseEntity.name} - {ta.section}
              </button>
            </li>
          ))}
      </ul>

      {selectedTeacherAssignment && (
        <div>
          <h2>Assignments for Selected Course/Section</h2>
          <ul className="assignment-list">
            {assignmentList.length > 0 &&
              assignmentList.map((assignment) => (
                <li key={assignment.id}>
                  <textarea
                    defaultValue={assignment.title}
                    onBlur={(event) =>
                      handleUpdateAssignment(assignment.id, {
                        ...assignment,
                        title: event.target.value,
                      })
                    }
                    className="assignment-textarea"
                  />
                  <textarea
                    defaultValue={assignment.description}
                    onBlur={(event) =>
                      handleUpdateAssignment(assignment.id, {
                        ...assignment,
                        description: event.target.value,
                      })
                    }
                    className="assignment-textarea"
                  />
                  <input
                    type="number"
                    defaultValue={assignment.totalMarks}
                    onBlur={(event) =>
                      handleUpdateAssignment(assignment.id, {
                        ...assignment,
                        totalMarks: event.target.value,
                      })
                    }
                    className="assignment-input"
                  />
                  <input
                    type="number"
                    defaultValue={assignment.weightage}
                    onBlur={(event) =>
                      handleUpdateAssignment(assignment.id, {
                        ...assignment,
                        weightage: event.target.value,
                      })
                    }
                    className="assignment-input"
                  />
                  <button
                    onClick={() => handleDeleteAssignment(assignment.id)}
                    className="delete-button"
                  >
                    Delete
                  </button>
                </li>
              ))}
          </ul>

          <h2>Add New Assignment</h2>
          <input
            type="text"
            name="title"
            value={newAssignment.title}
            onChange={handleNewAssignmentChange}
            placeholder="Title"
            className="new-assignment-input"
          />
          <textarea
            name="description"
            value={newAssignment.description}
            onChange={handleNewAssignmentChange}
            placeholder="Description"
            className="new-assignment-textarea"
          />
          <input
            type="number"
            name="totalMarks"
            value={newAssignment.totalMarks}
            onChange={handleNewAssignmentChange}
            placeholder="Total Marks"
            className="new-assignment-input"
          />
          <input
            type="number"
            name="weightage"
            value={newAssignment.weightage}
            onChange={handleNewAssignmentChange}
            placeholder="Weightage"
            className="new-assignment-input"
          />
          <button
            onClick={handleAssignmentSubmission}
            className="post-assignment-button"
          >
            Add Assignment
          </button>
        </div>
      )}
    </div>
  );
};

export default AssignmentManagement;
