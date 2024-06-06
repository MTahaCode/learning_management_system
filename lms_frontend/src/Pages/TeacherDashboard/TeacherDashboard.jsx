import React, { useEffect } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'

const TeacherDashboard = ({loginCredentials, setLoginCredentials}) => {

  const navigate = useNavigate();

  useEffect(() => {
      if (loginCredentials.loginType !== "teacher") {
          setLoginCredentials({
              loginId: 0,
              loginType: ""
          });
          navigate("/");
      }
  }, []);

  return (
    <div>
      <h1>Teacher Dashboard</h1>
      <button onClick={() => navigate("/teacherDashboard/attendanceManagement")}>Mark Attendances</button>
      <button onClick={() => navigate("/teacherDashboard/announcements")}>Post an Announcement</button>
      <Outlet />
    </div>
  )
}

export default TeacherDashboard
