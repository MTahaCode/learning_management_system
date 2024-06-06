import React, { useEffect } from "react";
import { useNavigate, Outlet } from "react-router-dom";
import "../../css/AdminDashboard.css";

const AdminDashboard = ({ loginCredentials, setLoginCredentials }) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (loginCredentials.loginType !== "admin") {
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
        <h2>Admin Dashboard</h2>
        <ul>
          <li>
            <button
              onClick={() => navigate("/adminDashboard/studentManagement")}
            >
              Manage Students
            </button>
          </li>
          <li>
            <button
              onClick={() => navigate("/adminDashboard/courseManagement")}
            >
              Manage Courses
            </button>
          </li>
          <li>
            <button
              onClick={() => navigate("/adminDashboard/teacherManagement")}
            >
              Manage Teachers
            </button>
          </li>
          <li>
            <button
              onClick={() =>
                navigate("/adminDashboard/teacherAssignmentManagement")
              }
            >
              Manage Teacher Assignments
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

export default AdminDashboard;
