import { useEffect, useState } from "react";
import "../../css/CourseMgmt.css";

const CourseMgmt = () => {
  const [addData, setAddData] = useState({
    name: "",
    credits: 0,
  });

  const [courseList, setCourseList] = useState([]);

  const [editForm, setEditForm] = useState({
    id: 0,
    name: "",
    credits: 0,
  });

  const getCourseList = () => {
    fetch("/course")
      .then((res) => res.json())
      .then((data) => {
        setCourseList(data);
        console.log("Fetched course list: ", data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  useEffect(() => {
    getCourseList();
  }, []);

  const addCourse = (event) => {
    event.preventDefault();

    if (addData.name === "" || addData.credits === 0) {
      alert("Name or Credits is empty");
      return;
    }

    fetch("/course", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(addData),
    })
      .then((res) => res.json())
      .then((data) => {
        setAddData({
          name: "",
          credits: 0,
        });
        getCourseList();
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  const handleDelete = (id) => {
    fetch(`/course/${id}`, {
      method: "DELETE",
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to delete Course");
        }
      })
      .then((data) => {
        getCourseList();
      })
      .catch((error) => {
        console.error("Error deleting course:", error);
      });
  };

  const putInEditSection = (id, name, credits) => {
    console.log("Editing course:", id, name, credits); // Add log here
    setEditForm({
      id: id,
      name: name,
      credits: credits,
    });
  };

  const handleEdit = (event) => {
    event.preventDefault();

    console.log("Submitting edit for id:", editForm.id); // Add log here

    if (editForm.id === 0) {
      alert("No entry selected");
      return;
    } else if (editForm.name === "" || editForm.credits === 0) {
      alert("Name or Credits is empty");
      return;
    }

    fetch(`/course/${editForm.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(editForm),
    })
      .then((res) => res.json())
      .then((data) => {
        getCourseList();
        setEditForm({
          id: 0,
          name: "",
          credits: 0,
        });
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  return (
    <div className="course-mgmt-container">
      <h1>Add a new Course</h1>
      <form onSubmit={addCourse} className="course-form">
        <label htmlFor="Name">Name: </label>
        <input
          name="Name"
          type="text"
          value={addData.name}
          onChange={(e) => setAddData({ ...addData, name: e.target.value })}
        />

        <label htmlFor="Credits">Credits: </label>
        <input
          name="Credits"
          type="number"
          value={addData.credits}
          onChange={(e) =>
            setAddData({ ...addData, credits: parseInt(e.target.value, 10) })
          }
        />

        <button type="submit">Add Course</button>
      </form>

      <h1>List of Courses</h1>
      <div className="course-list">
        {courseList.map((row) => (
          <div key={row.id} className="course-list-item">
            <p>
              {row.id} {row.name} {row.credits}
            </p>
            <div className="course-actions">
              <button
                onClick={() => putInEditSection(row.id, row.name, row.credits)}
              >
                Edit
              </button>
              <button
                className="delete-button"
                onClick={() => handleDelete(row.id)}
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>

      <h1>Edit Section</h1>
      <button
        className="clear-button"
        onClick={() => setEditForm({ id: 0, name: "", credits: 0 })}
      >
        Clear
      </button>
      <form onSubmit={handleEdit} className="course-form">
        <p>Id: {editForm.id}</p>

        <label htmlFor="editName">Edit Name: </label>
        <input
          name="editName"
          type="text"
          value={editForm.name}
          onChange={(e) => setEditForm({ ...editForm, name: e.target.value })}
        />

        <label htmlFor="editCredits">Edit Credits: </label>
        <input
          name="editCredits"
          type="number"
          value={editForm.credits}
          onChange={(e) =>
            setEditForm({ ...editForm, credits: parseInt(e.target.value, 10) })
          }
        />
        <button type="submit">Submit Edit</button>
      </form>
    </div>
  );
};

export default CourseMgmt;
