import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = ({loginCredentials, setLoginCredentials }) => {

    const navigate = useNavigate();

    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    });

    const checkLogin = async (url, loginType) => {
        const response = await (fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData),
        }));

        const data = await response.json();

        if (data.id !== 0) {
            return { loginId: data.id, loginType: loginType };
        }
        return { loginId: 0, loginType: null };
    }

    useEffect(() => {
        console.log(loginData);
        if (loginData.email !== "" && loginData.password !== "") {
            if (loginCredentials.loginId === 0) {
                alert("Cannot find login and password");
            }
            else if (loginCredentials.loginType === "student") {
                navigate("/studentDashboard");
            }
            else if (loginCredentials.loginType === "teacher") {
                navigate("/teacherDashboard");
            }
            else if (loginCredentials.loginType === "admin") {
                navigate("/adminDashboard");
            }
        }
    },[loginCredentials, navigate]);

    const handleLogin = async (event) => {

        event.preventDefault();

        //for admin
        if (loginData.email === "admin@admin.com" && loginData.password === "admin") {
            setLoginCredentials({
                loginId: 1,
                loginType: "admin"
            })
            return;
        }
        

        //for student
        const studentCredentials = await checkLogin("/student/getByEmailAndPassword", "student");
        
        if (studentCredentials.loginId !== 0) {
            setLoginCredentials(studentCredentials); 
            return;
        }

        //for teacher
        const teacherCredentials = await checkLogin("/teacher/getByEmailAndPassword", "teacher");

        if (teacherCredentials.loginId !== 0) {
            setLoginCredentials(teacherCredentials);
            return;
        }
    }
    
    return (
        <div>
            <form onSubmit={handleLogin}>
                <label htmlFor="Email">Email: </label>
                <input name="Email" 
                type="email" 
                value={loginData.email}
                onChange={e => setLoginData({...loginData, email: e.target.value})}
                />
                
                <label htmlFor="Password">Password: </label>
                <input name="Password" 
                type="text" 
                value={loginData.password}
                onChange={e => setLoginData({...loginData, password: e.target.value})}
                />

                <button type="submit" >Submit</button>
            </form>
        </div>
    )
}

export default LoginPage
