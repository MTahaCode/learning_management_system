import React, { useEffect } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'

const StudentDashboard = ({loginCredentials, setLoginCredentials}) => {
  
  const navigate = useNavigate();

  useEffect(() => {
      if (loginCredentials.loginType !== "student") {
          setLoginCredentials({
              loginId: 0,
              loginType: ""
          });
          navigate("/");
      }
  }, []);

  return (
    <div>
      <h1>Student Dashboard</h1>

      <div style={{display: 'flex'}}>
        <button onClick={() => navigate("/studentDashboard/enrollment")}>Enroll In Course</button>
        <button onClick={() => navigate("/studentDashboard/courseFeedback")}> Give Course Feedback</button>
        <button onClick={() => navigate("/studentDashboard/attemptQuiz")}>Attempt Quiz</button>
      </div>

      <Outlet />
    </div>
  )
}

export default StudentDashboard
