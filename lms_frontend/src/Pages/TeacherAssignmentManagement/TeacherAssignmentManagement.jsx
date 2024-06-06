import React, { useEffect, useRef, useState } from 'react'

const TeacherAssignmentManagement = () => {

    const [courseList, setCourseList] = useState([]);
    const [potentialAssignmentTeachers, setPotentialAssignmentTeachers] = useState([]);
    const [teacherAssignmentList, setTeacherAssignmentList] = useState([]);
    const [avaliableSections, setAvaliableSections] = useState([]);
    const [selectedSection, setSelectedSection] = useState("--");
    const sectionSelector = useRef(null);
    const sectionOptions = [
        { value: "--", label: "--"},
        { value: "A", label: "A"},
        { value: "B", label: "B"},
        { value: "C", label: "C"},
        { value: "D", label: "D"},
    ];


    const [selectedCourse, setSelectedCourse] = useState({
        course_id: 0,
        course_name: "",
    });

    const [selectedTeacher, setSelectedTeacher] = useState({
        teacher_id: 0,
        teacher_name: "",
    })

    useEffect(() => {
        getCourseList();
        getAllAssignments();
    }, []);

    useEffect(() => {
        if (selectedCourse.course_id !== 0 && selectedSection !== "--") {
            getPotentialAssignmentTeachers();
        }
        else if (selectedSection === "--") {
            setPotentialAssignmentTeachers([]);
            getSectionsOfSelectedCourse();
        }
    }, [selectedCourse, selectedSection]);

    const getPotentialAssignmentTeachers = () => {
        fetch(`/teacher/unassigned/course/${selectedCourse.course_id}/section/${selectedSection}`)
        .then(res => res.json())
        .then(data => {
            setPotentialAssignmentTeachers(data);
        })
    }

    const getCourseList = () => {
        fetch("/course")
        .then(res => res.json())
        .then(data => {
            setCourseList(data);
        }).catch((exc) => {
            console.log("Error: " + exc);
        })
    }

    const handleSelectCourse = (course) => {
        setSelectedTeacher({
            teacher_id: 0,
            teacher_name: "",
        })

        setPotentialAssignmentTeachers([]);
        
        setSelectedCourse({
            course_id: course.id,
            course_name: course.name,
        })
    }
    
    const handleSelectTeacher = (teacher) => {
        setSelectedTeacher({
            teacher_id: teacher.id,
            teacher_name: teacher.name,
        })
    }

    const assignTeacherToCourse = () => {

        if (selectedSection === "--" || 
            selectedCourse.course_id === 0 || 
            selectedTeacher.teacher_id === 0) {
            return;
        }
        console.log("saving teacher assignment")

        const teacherAssignmentJson = {
            "teacherEntity" : {
                id: selectedTeacher.teacher_id
            },
            "courseEntity": {
                id: selectedCourse.course_id
            },
            "section": selectedSection,
        }

        fetch("/teacherAssignment", {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(teacherAssignmentJson)
        })
        .then(res => res.json())
        .then(() => {
            getAllAssignments();
            clearAllSelections();
        })
    }

    const getAllAssignments = () => {
        fetch("/teacherAssignment")
        .then(res => res.json())
        .then(data => {
            console.log(data);
            setTeacherAssignmentList(data);
        })
    }

    const getSectionsOfSelectedCourse = () => {
        fetch(`/teacherAssignment/avaliableSectionsOfCourse/${selectedCourse.course_id}`)
        .then(res => res.json())
        .then(data => {
            setAvaliableSections(sectionOptions.filter(option => !data.includes(option.value)));
        })
    }

    const handleDeleteTeacherAssignment = (teacher_assignment_id) => {
        fetch(`/teacherAssignment/${teacher_assignment_id}`, {
            method: "DELETE",
        })
        .then(() => {
            getAllAssignments();
            clearAllSelections();
        })
    }

    const clearAllSelections = () => {
        setSelectedCourse({
            course_id: 0,
            course_name: "",
        })
        setSelectedTeacher({
            teacher_id: 0,
            teacher_name: "",
        })
        setTeacherAssignmentList([]);

        sectionSelector.current.selectedIndex = 0;
    }

    return (
        <div>
            <h1>List of Courses</h1>
            {courseList.length > 0 && courseList.map((row) => {
                return <>
                    <p>{row.id} {row.name} {row.credits}</p>
                    <button onClick = {() => handleSelectCourse(row)}>Select</button>
                </>
            })}

            <h1>Avaliable Sections: </h1>
            <select 
                name={`section-select`} 
                id={`section-select`} 
                ref={sectionSelector}
                //onChange={event => handleSectionChange(row.course_id, event.target.value)}
                onChange={event => setSelectedSection(event.target.value)}
                >
                {avaliableSections.map((option) => (
                <option value={option.value} key={option.value}>
                    {option.label}
                </option>
                ))}
            </select>

            <h1>Avaliable Teahers</h1>
            {potentialAssignmentTeachers.length > 0 && potentialAssignmentTeachers.map((row) => {
                return <>
                    <p>{row.id} {row.name} {row.email}</p>
                    <button onClick = {() => handleSelectTeacher(row)}>Select</button>

                    {/* <button onClick={() => handleDelete(row.course_id)}>delete</button>
                    <button onClick={() => putInEditSection(row.course_id, row.name, row.credits)}>edit</button> */}
                </>
            })}

            <h1>Selection: </h1>
            <p> Selected Course: {selectedCourse.course_id} {selectedCourse.course_name} </p>
            <p> Selected Teacher: {selectedTeacher.teacher_id} {selectedTeacher.teacher_name} </p>
            <button onClick={() => assignTeacherToCourse()}>Assign</button>

            <h1>List of all Teacher Assignments: </h1>
            <table>
                <thead>
                    <tr>
                        <th>Teacher Assignment Id</th>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Teacher ID</th>
                        <th>Teacher Name</th>
                        <th>Section</th>
                        <th>Semester</th>
                    </tr>
                </thead>
                <tbody>
                    {teacherAssignmentList.length > 0 && teacherAssignmentList.map(ta => {
                        return (
                            <tr>
                                <td>{ta.id}</td>
                                <td>{ta.courseEntity.id}</td>
                                <td>{ta.courseEntity.name}</td>
                                <td>{ta.teacherEntity.id}</td>
                                <td>{ta.teacherEntity.name}</td>
                                <td>{ta.section}</td>
                                <td>{ta.semester || "Empty"}</td>
                                <td><button onClick={() => handleDeleteTeacherAssignment(ta.id)}>Remove Assignment</button></td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default TeacherAssignmentManagement
