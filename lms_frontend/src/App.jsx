import { useEffect, useState } from "react"
import logo from './logo.svg';
import './App.css';
import "./pages/StudentMgmt/StudentMgmt.jsx"
import StudentMgmt from './pages/StudentMgmt/StudentMgmt.jsx';
import CourseMgmt from './pages/CourseMgmt/CourseMgmt.jsx';
import LoginPage from './pages/Login/LoginPage.jsx';
import TeacherAssignmentManagement from "./pages/TeacherAssignmentManagement/TeacherAssignmentManagement.jsx";
import TeacherMgmt from "./pages/TeacherMgmt/TeacherMgmt.jsx";
import EnrollManagement from "./pages/EnrollManagement/EnrollManagement.jsx";
import AttendanceMgmt from "./pages/AttendanceMgmt/AttendanceMgmt.jsx";
import AdminDashboard from "./pages/AdminDashboard/AdminDashboard.jsx";
import TeacherDashboard from "./pages/TeacherDashboard/TeacherDashboard.jsx";
import StudentDashboard from "./pages/StudentDashboard/StudentDashboard.jsx";
import CourseFeedback from "./pages/CourseFeedback/CourseFeedback.jsx";
import {Route, Routes, useNavigate } from "react-router-dom";

function App() {

  const navigate = useNavigate();

  const [loginCredentials, setLoginCredentials] = useState({loginId: 0, loginType: null});

  useEffect(() => {
    if (loginCredentials.loginId === 0) {
      navigate("/");
    }
  }, [loginCredentials, navigate]);

  return (
    <div>
      {/* <p>{loginCredentials.loginId}</p>
      <p>{loginCredentials.loginType}</p> */}

      <Routes>
        <Route path="/" element={<LoginPage loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} />
        <Route path="/studentDashboard" element={<StudentDashboard loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} >
          <Route path="enrollment" element={<EnrollManagement loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials} />} />
          <Route path="courseFeedback" element={<CourseFeedback loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>}/>
          {/* attempt quiz */}
        </Route>
        <Route path="/teacherDashboard" element={<TeacherDashboard loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} >
          <Route path="attendanceManagement" element={<AttendanceMgmt loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} />
        </Route>
        <Route path="/adminDashboard" element={<AdminDashboard loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} >
          <Route path="studentManagement" element={<StudentMgmt />} />
          <Route path="courseManagement" element={<CourseMgmt />} />
          <Route path="teacherManagement" element={<TeacherMgmt />} />
          <Route path="teacherAssignmentManagement" element={<TeacherAssignmentManagement loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} />

        </Route>
      </Routes>
    </div>
  );
}

export default App;
