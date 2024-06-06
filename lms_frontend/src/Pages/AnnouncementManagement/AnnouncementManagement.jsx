import React, { useEffect, useState } from "react";
import "../../css/AnnouncementManagement.css";

const AnnouncementManagement = ({ loginCredentials, setLoginCredentials }) => {
  const [assignmentList, setAssignmentList] = useState([]);
  const [announcementList, setAnnouncementList] = useState([]);
  const [selectedAssignment, setSelectedAssignment] = useState(null);
  const [newAnnouncement, setNewAnnouncement] = useState("");

  useEffect(() => {
    getTeacherAssignments();
  }, []);

  const getTeacherAssignments = () => {
    fetch(`/teacherAssignment/teacher/${loginCredentials.loginId}`)
      .then((res) => res.json())
      .then((data) => {
        setAssignmentList(data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const getAnnouncementsByAssignment = (assignmentId) => {
    fetch(`/announcements/teacherAssignment/${assignmentId}`)
      .then((res) => res.json())
      .then((data) => {
        setAnnouncementList(data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleAssignmentSelection = (assignmentId) => {
    setSelectedAssignment(assignmentId);
    getAnnouncementsByAssignment(assignmentId);
  };

  const handleNewAnnouncementChange = (event) => {
    setNewAnnouncement(event.target.value);
  };

  const handleAnnouncementSubmission = () => {
    const announcementData = {
      teacherAssignmentEntity: {
        id: selectedAssignment,
      },
      announcementData: newAnnouncement,
      announcementDate: new Date(),
    };

    fetch(`/announcements`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(announcementData),
    })
      .then((res) => res.json())
      .then((data) => {
        setNewAnnouncement("");
        getAnnouncementsByAssignment(selectedAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleDeleteAnnouncement = (announcementId) => {
    fetch(`/announcements/${announcementId}`, {
      method: "DELETE",
    })
      .then(() => {
        getAnnouncementsByAssignment(selectedAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  const handleUpdateAnnouncement = (announcementId, newContent) => {
    const updatedAnnouncement = {
      id: announcementId,
      teacherAssignmentEntity: {
        id: selectedAssignment,
      },
      announcementData: newContent,
      announcementDate: new Date(),
    };

    fetch(`/announcements/${announcementId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedAnnouncement),
    })
      .then((res) => res.json())
      .then(() => {
        getAnnouncementsByAssignment(selectedAssignment);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  return (
    <div className="announcement-container">
      <h1>Manage Announcements</h1>
      <h2>Select an Assignment</h2>
      <ul className="assignment-list">
        {assignmentList.length > 0 &&
          assignmentList.map((assignment) => (
            <li key={assignment.id}>
              <button onClick={() => handleAssignmentSelection(assignment.id)}>
                {assignment.courseEntity.name} - {assignment.section}
              </button>
            </li>
          ))}
      </ul>

      {selectedAssignment && (
        <div>
          <h2>Announcements for Selected Assignment</h2>
          <ul className="announcement-list">
            {announcementList.length > 0 &&
              announcementList.map((announcement) => (
                <li key={announcement.id}>
                  <textarea
                    defaultValue={announcement.announcementData}
                    onBlur={(event) =>
                      handleUpdateAnnouncement(
                        announcement.id,
                        event.target.value
                      )
                    }
                    className="announcement-textarea"
                  />
                  <button
                    onClick={() => handleDeleteAnnouncement(announcement.id)}
                    className="delete-button"
                  >
                    Delete
                  </button>
                </li>
              ))}
          </ul>

          <h2>Add New Announcement</h2>
          <textarea
            value={newAnnouncement}
            onChange={handleNewAnnouncementChange}
            className="new-announcement-textarea"
          />
          <button
            onClick={handleAnnouncementSubmission}
            className="post-announcement-button"
          >
            Post Announcement
          </button>
        </div>
      )}
    </div>
  );
};

export default AnnouncementManagement;
