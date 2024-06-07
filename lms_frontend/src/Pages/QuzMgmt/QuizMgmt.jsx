import React, { useEffect, useState } from 'react'

const QuizMgmt = ({loginCredentials, setLoginCredentials}) => {

    const [quizInfo, setQuizInfo] = useState({
        id: null,
        title: "",
        description: "",
        question1: "",
        question2: "",
        question3: "",
        question4: "",
        totalMarks: 0.0,
        weightage: 0.0,
    });

    const [teacherCourseId, setTeacherCourseId] = useState(null);
    const [teacherAssignmentList, setTeacherAssignmentList] = useState([]);
    const [selectedTeacherAssignment, setSelectedAssignment] = useState(0);

    useEffect(() => {
        getCoursesOfTeacher();
        if (teacherCourseId !== null) {
            getAllQuizes();
        }
    }, [teacherCourseId]);

    useEffect(() => {
        console.log("Selected Teacher Assignment: ", selectedTeacherAssignment);
    }, [selectedTeacherAssignment]);

    const getAllQuizes = () => {
        if (teacherCourseId === null) {
            return;
        }

        fetch(`/teacherAssignment/${teacherCourseId}`)
        .then(res => res.json())
        .then(data => {
            console.log(data.quizList);
            if (data.quizList.length > 0) {
                setSelectedAssignment(data);
            }
        })
    }

    const handleQuizSave = (event) => {
        event.preventDefault();

        const { id, title, description, question1, question2, question3, question4, totalMarks, weightage } = quizInfo;

        if (
            !title.trim() ||
            !description.trim() ||
            !question1.trim() ||
            !question2.trim() ||
            !question3.trim() ||
            !question4.trim() ||
            totalMarks <= 0 ||
            weightage <= 0 ||
            !teacherCourseId
        ) {
            alert("All fields must be filled out and valid.");
            return;
        }

        const quizData = quizInfo;
        quizInfo["teacher_course"] = {
            id: teacherCourseId
        }

        console.log(quizInfo);
        fetch("/quiz", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(quizData),
        })
        .then(res => res.json())
        .then(data => {
            setQuizInfo ({
                id: null,
                title: "",
                description: "",
                question1: "",
                question2: "",
                question3: "",
                question4: "",
                totalMarks: 0.0,
                weightage: 0.0,
            })
        })
    }

    const getCoursesOfTeacher = () => {
            fetch(`/teacherAssignment/teacher/${loginCredentials.loginId}`)
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setTeacherAssignmentList(data);
            });
        };

    const handleSelectTeacherAssignment = (ta_id) => {
        console.log(ta_id);
        // setQuizInfo({
        //     ...quizInfo,
        //     teacher_course: {
        //         id: ta_id,
        //     }
        // })
        setTeacherCourseId(ta_id);
    }   

    const handleDeleteQuiz = (quizId) => {
        console.log(quizId);

        fetch(`/quiz/${quizId}`, {
            method: "DELETE",
        })
        .then(() => {
            getAllQuizes();
        })
    }

    return (
        <div>

        <h1>My Courses</h1>
            <table className="courses-table">
                <thead>
                    <tr>
                        <th>TA Id</th>
                        <th>Course Id</th>
                        <th>Name</th>
                        <th>Credits</th>
                        <th>Section</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {teacherAssignmentList.length > 0 &&
                    teacherAssignmentList.map((ta, index) => (
                    <tr key={`ta-${index}`}>
                        <td>{ta.id}</td>
                        <td>{ta.courseEntity.id}</td>
                        <td>{ta.courseEntity.name}</td>
                        <td>{ta.courseEntity.credits}</td>
                        <td>{ta.section}</td>
                        <td>
                        <button
                            onClick={() => {
                                handleSelectTeacherAssignment(ta.id);
                                getAllQuizes();
                            }}
                        >
                            Select Class
                        </button>
                        </td>
                    </tr>
                    ))}
            </tbody>
            </table>
        
            <form onSubmit={handleQuizSave} className="student-form">
                {/* <p>Id: {quizInfo.id}</p> */}

                <label htmlFor="Title">Title: </label>
                <input
                name="Title"
                type="text"
                value={quizInfo.title}
                onChange={(e) => setQuizInfo({ ...quizInfo, title: e.target.value })}
                />

                <label htmlFor="description">Description: </label>
                <input
                name="description"
                type="text"
                value={quizInfo.description}
                onChange={(e) => setQuizInfo({ ...quizInfo, description: e.target.value })}
                />

                <label htmlFor="question1">Question 1: </label>
                <input
                name="question1"
                type="text"
                value={quizInfo.question1}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, question1: e.target.value })
                } />

                <label htmlFor="question2">Question 2: </label>
                <input
                name="question2"
                type="text"
                value={quizInfo.question2}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, question2: e.target.value })
                } />

                <label htmlFor="question3">Question 3: </label>
                <input
                name="question3"
                type="text"
                value={quizInfo.question3}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, question3: e.target.value })
                } />

                <label htmlFor="question4">Question 4: </label>
                <input
                name="question4"
                type="text"
                value={quizInfo.question4}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, question4: e.target.value })
                }
                />

                <label htmlFor="totalMarks">Total Marks: </label>
                <input
                name="question4"
                type="number"
                value={quizInfo.totalMarks}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, totalMarks: e.target.value })
                }
                />

                <label htmlFor="weightage">Weightage : </label>
                <input
                name="weightage"
                type="number"
                value={quizInfo.weightage}
                onChange={(e) =>
                    setQuizInfo({ ...quizInfo, weightage: e.target.value })
                }
                />

                <button type="submit">Submit Edit</button>
            </form>

            <h2>Quiz List</h2>
            <table className="quiz-info-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Question 1</th>
                        <th>Question 2</th>
                        <th>Question 3</th>
                        <th>Question 4</th>
                        <th>Total Marks</th>
                        <th>Weightage</th>
                    </tr>
                </thead>
                <tbody>
                    {selectedTeacherAssignment &&
                    selectedTeacherAssignment.quizList.length > 0 && 
                    selectedTeacherAssignment.quizList.map((quiz, index) => (
                        <tr key={index}>
                            <td>{quiz.id}</td>
                            <td>{quiz.title}</td>
                            <td>{quiz.description}</td>
                            <td>{quiz.question1}</td>
                            <td>{quiz.question2}</td>
                            <td>{quiz.question3}</td>
                            <td>{quiz.question4}</td>
                            <td>{quiz.totalMarks}</td>
                            <td>{quiz.weightage}</td>
                            <button onClick={() => handleDeleteQuiz(quiz.id)}>Delete Quiz</button>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default QuizMgmt
