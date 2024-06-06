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
import {Route, Routes, useNavigate } from "react-router-dom";
import CourseFeedback from "./pages/CourseFeedback/CourseFeedback.jsx";

function App() {

  const navigate = useNavigate();

  const [loginCredentials, setLoginCredentials] = useState({loginId: 0, loginType: null});

  // useEffect(() => {
  //   if (loginCredentials.loginId === 0) {
  //     navigate("/");
  //   }
  // }, [loginCredentials, navigate]);

  return (
    <div>
      <p>{loginCredentials.loginId}</p>
      <p>{loginCredentials.loginType}</p>

      {/* <TeacherAssignmentManagement /> */}

      <Routes>
        <Route path="/" element={<LoginPage loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} />
        <Route path="/studentManagement" element={<StudentMgmt />} />
        <Route path="/courseManagement" element={<CourseMgmt />} />
        <Route path="/teacherManagement" element={<TeacherMgmt />} />
        <Route path="/enrollment" element={<EnrollManagement loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials} />} />
        <Route path="/courseFeedback" element={<CourseFeedback loginCredentials={loginCredentials}/>} />
        <Route path="/attendanceManagement" element={<AttendanceMgmt loginCredentials={loginCredentials} setLoginCredentials={setLoginCredentials}/>} />

      </Routes>
    </div>
  );
}

export default App;
