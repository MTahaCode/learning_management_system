import React, { useEffect } from 'react'
import { useNavigate, Outlet } from 'react-router-dom'

const AdminDashboard = ({loginCredentials, setLoginCredentials}) => {

    const navigate = useNavigate();

    useEffect(() => {
        if (loginCredentials.loginType !== "admin") {
            setLoginCredentials({
                loginId: 0,
                loginType: ""
            });
            navigate("/");
        }
    }, []);

    return (
        <div>
            <h1>Admin Dashboard</h1>
            <div style={{display: 'flex'}}>
                <button onClick={() => navigate("/adminDashboard/studentManagement")}>Manage Students</button>
                <button onClick={() => navigate("/adminDashboard/courseManagement")}>Manage Courses</button>
                <button onClick={() => navigate("/adminDashboard/teacherManagement")}>Manage Teachers</button>
                <button onClick={() => navigate("/adminDashboard/teacherAssignmentManagement")}>Manage Teacher Assignments</button>
            </div>
            
            <Outlet />
        </div>
    )
}

export default AdminDashboard
