import React, { useEffect, useState } from 'react'

const EnrollManagement = ({loginCredentials, setLoginCredentials }) => {
    
    const [EnrolledList, setEnrolledList] = useState([]);
    const [courseList, setCourseList] = useState([]);
    const sectionOptions = [
        { value: "--", label: "--"},
        { value: "A", label: "A"},
        { value: "B", label: "B"},
        { value: "C", label: "C"},
        { value: "D", label: "D"},
    ];

    useEffect(() => {
        getCourseListNotEnrolled();
        getEnrolledCourses();
        console.log(EnrolledList);
    }, []);

    const getEnrolledCourses = () => {
        fetch(`/student/enrollments/${loginCredentials.loginId}`)
        .then(res => res.json())
        .then((data) => {
            console.log(data);
            setEnrolledList(data);
        })
    }

    const getCourseListNotEnrolled = () => {
        //get all courses not enrolled by the student
        //`/enrollment/enrollable_courses/${loginCredentials.loginId}`

        fetch(`/course/enrollable-courses/${loginCredentials.loginId}`)
        .then(res => res.json())
        .then(data => {
            setCourseList(data.map(course => {
                return {
                    ...course,
                    section: "--"
                }
            }))
        }).catch((exc) => {
          console.log("Error: " + exc);
        })
    }

    const handleSectionChange = (course_id, value) => {
        courseList.find(course => course.course_id === course_id)
        .section = value;
        console.log(courseList);
    }

    const handleEnrollmentConfirmation = () => {
        courseList.filter(course => course.section !== "--")
        .map(course => {
            return {
                grade: course.grade,
                section: course.section,
                semester: course.semester,
                courseEntity: {
                    id: course.id,
                },
                studentEntity: {
                    id: loginCredentials.loginId
                },
            }
        })
        .forEach(course => {

            console.log(course);

            fetch(`/enrollment`, {
                method: "POST",
                headers: {
                  "Content-Type": "application/json"
                },
                body: JSON.stringify(course)
            }).then(res => res.json())
            .then((data) => {
                getCourseListNotEnrolled();
                getEnrolledCourses();

            }).catch((exc) => {
                console.log("Error: " + exc);
            })
        })
    }

    const handleDeleteEmrollment = (enrolled_id) => {
        fetch(`/enrollment/${enrolled_id}`, {
            method: "DELETE",
        })
        //.then(res => res.json())
        .then(() => {
            getCourseListNotEnrolled();
            getEnrolledCourses();
        })
    }
  
    return (
    <div>
        <h1>Enroll to a course</h1>
        <p>show a list of courses he can enroll to and then from there select a section and then confirm at the bottom</p>
        
        <h1>List of Courses that can be enrolled to </h1>
        <table>
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Credits</th>
                <th>Grade</th>
                <th>Section</th>
            </tr>
            {courseList.length > 0 && courseList.map((row, index) => (
                <tr key={index}>
                    <td>{row.id}</td>
                    <td>{row.name}</td>
                    <td>{row.credits}</td>
                    <td>{row.grade}</td>
                    <td>
                    <select 
                        name={`select-${row.course_id}`} 
                        id={`select-${row.course_id}`} 
                        onChange={event => handleSectionChange(row.course_id, event.target.value)}>
                        {sectionOptions.map((option) => (
                        <option value={option.value} key={option.value}>
                            {option.label}
                        </option>
                        ))}
                    </select>
                    </td>
                </tr>
                ))}
        </table>
        <button onClick={handleEnrollmentConfirmation}>Confirm enrollment in courses</button>
            

        <h1>List of all his enrollments</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Student ID</th>
                    <th>Course ID</th>
                    <th>Semester</th>
                    <th>Section</th>
                    <th>Grade</th>
                </tr>
            </thead>
            <tbody>
                {EnrolledList.length > 0 && EnrolledList.map(enrolled => {
                    return (
                        <tr key={enrolled.id}>
                            <td>{enrolled.id}</td>
                            <td>{enrolled.studentEntity.id}</td>
                            <td>{enrolled.courseEntity.id}</td>
                            <td>{enrolled.semester || "empty"}</td>
                            <td>{enrolled.section}</td>
                            <td>{enrolled.grade || "empty"}</td>
                            <td><button onClick={() => handleDeleteEmrollment(enrolled.id)} >Remove Enrollment</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
    </div>
  )
}

export default EnrollManagement
