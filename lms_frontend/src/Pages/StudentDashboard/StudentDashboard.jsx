import React, { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import '../../css/StudentDashboard.css';

const StudentDashboard = ({ loginCredentials, setLoginCredentials }) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (loginCredentials.loginType !== "student") {
      setLoginCredentials({
        loginId: 0,
        loginType: ""
      });
      navigate("/");
    }
  }, [loginCredentials, navigate, setLoginCredentials]);

  return (
    <div className="dashboard-container">
      <nav className="sidebar">
        <h2>Student Dashboard</h2>
        <ul>
          <li>
            <button onClick={() => navigate("/studentDashboard/enrollment")}>Enroll In Course</button>
          </li>
          <li>
            <button onClick={() => navigate("/studentDashboard/courseFeedback")}>Give Course Feedback</button>
          </li>
          <li>
            <button onClick={() => navigate("/studentDashboard/attemptQuiz")}>Attempt Quiz</button>
          </li>
        </ul>
      </nav>
      <main className="main-content">
        <Outlet />
      </main>
    </div>
  );
};

export default StudentDashboard;
