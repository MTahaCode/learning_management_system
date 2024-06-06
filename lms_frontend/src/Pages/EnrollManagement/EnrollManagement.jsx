import React, { useEffect, useState } from "react";
import "../../css/EnrollManagement.css";

const EnrollManagement = ({ loginCredentials, setLoginCredentials }) => {
  const [EnrolledList, setEnrolledList] = useState([]);
  const [courseList, setCourseList] = useState([]);
  const sectionOptions = [
    { value: "--", label: "--" },
    { value: "A", label: "A" },
    { value: "B", label: "B" },
    { value: "C", label: "C" },
    { value: "D", label: "D" },
  ];

  useEffect(() => {
    getCourseListNotEnrolled();
    getEnrolledCourses();
  }, []);

  const getEnrolledCourses = () => {
    fetch(`/student/enrollments/${loginCredentials.loginId}`)
      .then((res) => res.json())
      .then((data) => {
        setEnrolledList(data);
      })
      .catch((err) => {
        console.error("Error fetching enrolled courses:", err);
      });
  };

  const getCourseListNotEnrolled = () => {
    fetch(`/course/enrollable-courses/${loginCredentials.loginId}`)
      .then((res) => res.json())
      .then((data) => {
        setCourseList(
          data.map((course) => ({
            ...course,
            section: "--",
          }))
        );
      })
      .catch((exc) => {
        console.error("Error fetching courses:", exc);
      });
  };

  const handleSectionChange = (course_id, value) => {
    setCourseList((prevList) =>
      prevList.map((course) =>
        course.id === course_id ? { ...course, section: value } : course
      )
    );
  };

  const handleEnrollmentConfirmation = () => {
    const coursesToEnroll = courseList
      .filter((course) => course.section !== "--")
      .map((course) => ({
        grade: course.grade,
        section: course.section,
        semester: course.semester,
        courseEntity: { id: course.id },
        studentEntity: { id: loginCredentials.loginId },
      }));

    Promise.all(
      coursesToEnroll.map((course) =>
        fetch(`/enrollment`, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(course),
        })
      )
    )
      .then((responses) => {
        const failedResponses = responses.filter((res) => !res.ok);
        if (failedResponses.length > 0) {
          throw new Error("Some enrollments failed");
        }
        return Promise.all(responses.map((res) => res.json()));
      })
      .then(() => {
        getCourseListNotEnrolled();
        getEnrolledCourses();
      })
      .catch((exc) => {
        console.error("Error during enrollment:", exc);
      });
  };

  const handleDeleteEnrollment = (enrolled_id) => {
    fetch(`/enrollment/${enrolled_id}`, {
      method: "DELETE",
    })
      .then(() => {
        getCourseListNotEnrolled();
        getEnrolledCourses();
      })
      .catch((err) => {
        console.error("Error deleting enrollment:", err);
      });
  };

  return (
    <div className="enroll-container">
      <h1>Enroll in a Course</h1>
      <p>Select a course and section to enroll.</p>

      <h2>Courses Available for Enrollment</h2>
      <table className="course-table">
        <thead>
          <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Credits</th>
            <th>Section</th>
          </tr>
        </thead>
        <tbody>
          {courseList.length > 0 &&
            courseList.map((row, index) => (
              <tr key={index}>
                <td>{row.id}</td>
                <td>{row.name}</td>
                <td>{row.credits}</td>
                <td>
                  <select
                    name={`select-${row.id}`}
                    id={`select-${row.id}`}
                    onChange={(event) =>
                      handleSectionChange(row.id, event.target.value)
                    }
                    value={row.section}
                  >
                    {sectionOptions.map((option) => (
                      <option value={option.value} key={option.value}>
                        {option.label}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
      <button onClick={handleEnrollmentConfirmation} className="confirm-btn">
        Confirm Enrollment
      </button>

      <h2>Your Enrollments</h2>
      <table className="enrollment-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Student ID</th>
            <th>Course ID</th>
            <th>Section</th>
            <th>Grade</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {EnrolledList.length > 0 &&
            EnrolledList.map((enrolled) => (
              <tr key={enrolled.id}>
                <td>{enrolled.id}</td>
                <td>{enrolled.studentEntity.id}</td>
                <td>{enrolled.courseEntity.id}</td>
                <td>{enrolled.section}</td>
                <td>{enrolled.grade || "empty"}</td>
                <td>
                  <button
                    onClick={() => handleDeleteEnrollment(enrolled.id)}
                    className="delete-btn"
                  >
                    Remove
                  </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
};

export default EnrollManagement;
