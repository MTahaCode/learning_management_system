import React, { useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import "../../css/TeacherDashboard.css";

const TeacherDashboard = ({ loginCredentials, setLoginCredentials }) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (loginCredentials.loginType !== "teacher") {
      setLoginCredentials({
        loginId: 0,
        loginType: "",
      });
      navigate("/");
    }
  }, [loginCredentials, navigate, setLoginCredentials]);

  return (
    <div className="dashboard-container">
      <nav className="sidebar">
        <h2>Teacher Dashboard</h2>
        <ul>
          <li>
            <button
              onClick={() => navigate("/teacherDashboard/attendanceManagement")}
            >
              Mark Attendances
            </button>
          </li>
          <li>
            <button onClick={() => navigate("/teacherDashboard/announcements")}>
              Post an Announcement
            </button>
          </li>
          <li>
            <button onClick={() => navigate("/teacherDashboard/quizManagement")}>
              Make a Quiz
            </button>
          </li>
        </ul>
      </nav>
      <main className="main-content">
        <Outlet />
      </main>
    </div>
  );
};

export default TeacherDashboard;
