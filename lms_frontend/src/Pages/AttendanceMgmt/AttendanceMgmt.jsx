import React, { useEffect, useState } from "react";
import "../../css/AttendanceMgmt.css";

const AttendanceMgmt = ({ loginCredentials, setLoginCredentials }) => {
  const [teacherAssignmentList, setTeacherAssignmentList] = useState([]);
  const [enrollmentList, setEnrollmentList] = useState([]);
  const [uniqueDates, setUniqueDates] = useState([]);

  useEffect(() => {
    getCoursesOfTeacher();
  }, []);

  useEffect(() => {
    if (enrollmentList.length > 0) {
      extractUniqueDates();
    }
    console.log("Enrollment List: ", enrollmentList);
  }, [enrollmentList]);

  const getCoursesOfTeacher = () => {
    fetch(`/teacherAssignment/teacher/${loginCredentials.loginId}`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setTeacherAssignmentList(data);
      });
  };

  const handleGetAttendance = (course_id, section) => {
    fetch(`/enrollment/course/${course_id}/section/${section}`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setEnrollmentList(data);
      })
      .finally(() => {
        extractUniqueDates();
      });
  };

  const extractUniqueDates = () => {
    const dates = new Set();
    enrollmentList.forEach((enrollment) => {
      enrollment.attendanceList.forEach((attendance) => {
        const date = new Date(attendance.dateTime).toISOString();
        dates.add(date);
      });
    });
    console.log("Dates: ", dates);
    setUniqueDates([...dates].sort());
  };

  const addAttendanceToAllEnrollments = (dateTime, type) => {
    const newAttendance = {
      id: null,
      dateTime,
      type,
    };
    const updatedEnrollments = enrollmentList.map((enrollment) => {
      const updatedAttendanceList = [
        ...enrollment.attendanceList,
        newAttendance,
      ];
      return {
        ...enrollment,
        attendanceList: updatedAttendanceList,
      };
    });
    setEnrollmentList(updatedEnrollments);
  };

  const handleAddAttendance = () => {
    const dateTime = new Date().toISOString();
    const type = "P";
    addAttendanceToAllEnrollments(dateTime, type);
  };

  const handleAttendanceUpdate = (newType, date, enrollmentId) => {
    const updatedAttendanceList = enrollmentList.map((enrollment) => {
      if (enrollment.id === enrollmentId) {
        const updatedList = enrollment.attendanceList.map((attendance) => {
          if (new Date(attendance.dateTime).toISOString() === date) {
            return { ...attendance, type: newType };
          }
          return attendance;
        });
        return { ...enrollment, attendanceList: updatedList };
      }
      return enrollment;
    });
    console.log("Update Attendance: ", updatedAttendanceList);
    setEnrollmentList(updatedAttendanceList);
  };

  const handleSave = () => {
    enrollmentList.forEach((enrollment) => {
      enrollment.attendanceList.forEach((attendance) => {
        attendance["enrollmentEntity"] = {
          id: enrollment.id,
        };
        console.log("Attendance: ", attendance);
        fetch("/attendance/createOrUpdate", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(attendance),
        })
          .then((res) => res.json())
          .then((data) => {
            console.log(data);
          })
          .catch((error) => {
            console.error("Error saving attendance:", error);
          });
      });
    });
  };

  return (
    <div className="attendance-container">
      <h1>My Courses</h1>
      <table className="courses-table">
        <thead>
          <tr>
            <th>Course Id</th>
            <th>Name</th>
            <th>Credits</th>
            <th>Section</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {teacherAssignmentList.length > 0 &&
            teacherAssignmentList.map((ta) => (
              <tr key={ta.courseEntity.id}>
                <td>{ta.courseEntity.id}</td>
                <td>{ta.courseEntity.name}</td>
                <td>{ta.courseEntity.credits}</td>
                <td>{ta.section}</td>
                <td>
                  <button
                    onClick={() =>
                      handleGetAttendance(ta.courseEntity.id, ta.section)
                    }
                  >
                    Select Class
                  </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>

      <table className="attendance-table">
        <thead>
          <tr>
            <th>Student Id</th>
            <th>Student Name</th>
            {uniqueDates.length > 0 &&
              uniqueDates.map((date, index) => <th key={index}>{date}</th>)}
          </tr>
        </thead>
        <tbody>
          {enrollmentList.length > 0 &&
            enrollmentList.map((enrollment) => (
              <tr key={enrollment.id}>
                <td>{enrollment.studentEntity.id}</td>
                <td>{enrollment.studentEntity.name}</td>
                {uniqueDates.length > 0 &&
                  uniqueDates.map((date, index) => {
                    const attendanceRecord = enrollment.attendanceList.find(
                      (attendance) =>
                        new Date(attendance.dateTime).toISOString() === date
                    );
                    return (
                      <td key={index}>
                        <select
                          value={attendanceRecord ? attendanceRecord.type : ""}
                          onChange={(e) =>
                            handleAttendanceUpdate(
                              e.target.value,
                              date,
                              enrollment.id
                            )
                          }
                        >
                          <option value="">--</option>
                          <option value="A">A</option>
                          <option value="P">P</option>
                        </select>
                      </td>
                    );
                  })}
              </tr>
            ))}
        </tbody>
      </table>
      <button onClick={handleSave}>Save</button>
      <button onClick={handleAddAttendance}>Add new attendance</button>
    </div>
  );
};

export default AttendanceMgmt;
