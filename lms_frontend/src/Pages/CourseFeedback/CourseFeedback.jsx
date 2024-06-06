import React, { useEffect, useState } from 'react';

const CourseFeedback = ({ loginCredentials }) => {
    const [enrolledCourses, setEnrolledCourses] = useState([]);
    const [feedback, setFeedback] = useState({});
    const [existingFeedbacks, setExistingFeedbacks] = useState({});

    useEffect(() => {
        getEnrolledCourses();
        getExistingFeedbacks();
    }, []);

    const getEnrolledCourses = () => {
        fetch(`/student/enrollments/${loginCredentials.loginId}`)
            .then(res => res.json())
            .then(data => {
                setEnrolledCourses(data);
            })
            .catch(exc => {
                console.log("Error: " + exc);
            });
    }

    const getExistingFeedbacks = () => {
        fetch(`/feedback`)
            .then(res => res.json())
            .then(data => {
                const feedbackMap = {};
                data.forEach(fb => {
                    if (fb.enrollmentEntity.studentEntity.id === loginCredentials.loginId) {
                        feedbackMap[fb.enrollmentEntity.courseEntity.id] = fb;
                    }
                });
                setExistingFeedbacks(feedbackMap);
            })
            .catch(exc => {
                console.log("Error: " + exc);
            });
    }

    const handleFeedbackChange = (courseId, value) => {
        setFeedback({
            ...feedback,
            [courseId]: value
        });
    }

    const handleSubmitFeedback = (enrollmentId, courseId) => {
        if (existingFeedbacks[courseId]) {
            alert("Feedback for this course has already been submitted.");
            return;
        }

        const feedbackData = {
            enrollmentEntity: { id: enrollmentId },
            feedbackData: feedback[courseId]
        };

        fetch(`/feedback`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(feedbackData)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                alert("Feedback submitted successfully");
                getExistingFeedbacks();
            })
            .catch(exc => {
                console.log("Error: " + exc);
            });
    }

    return (
        <div>
            <h1>Submit Course Feedback</h1>
            <table>
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Feedback</th>
                        <th>Submit</th>
                    </tr>
                </thead>
                <tbody>
                    {enrolledCourses.length > 0 && enrolledCourses.map(course => (
                        <tr key={course.courseEntity.id}>
                            <td>{course.courseEntity.id}</td>
                            <td>{course.courseEntity.name}</td>
                            <td>
                                {existingFeedbacks[course.courseEntity.id] ? (
                                    <span>Already Submitted</span>
                                ) : (
                                    <textarea
                                        value={feedback[course.courseEntity.id] || ''}
                                        onChange={e => handleFeedbackChange(course.courseEntity.id, e.target.value)}
                                    />
                                )}
                            </td>
                            <td>
                                <button 
                                    onClick={() => handleSubmitFeedback(course.id, course.courseEntity.id)}
                                    disabled={!!existingFeedbacks[course.courseEntity.id]}
                                >
                                    Submit Feedback
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default CourseFeedback;
