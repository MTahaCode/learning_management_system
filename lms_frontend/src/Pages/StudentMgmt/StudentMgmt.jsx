import { useEffect, useState } from "react";
import "../../css/StudentMgmt.css";

const StudentMgmt = () => {
  const [formdata, setformdata] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [studentList, setStudentList] = useState([]);

  const [editForm, setEditForm] = useState({
    id: 0,
    name: "",
    email: "",
    password: "",
  });

  const getStudentList = () => {
    fetch("/student")
      .then((res) => res.json())
      .then((data) => {
        setStudentList(data);
        console.log(data);
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  useEffect(() => {
    getStudentList();
  }, []);

  const addStudent = (event) => {
    event.preventDefault();

    if (
      formdata.name === "" ||
      formdata.email === "" ||
      formdata.password === ""
    ) {
      alert("Name or Email or Password is empty");
      return;
    }

    fetch("/student", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formdata),
    })
      .then((res) => res.json())
      .then((data) => {
        setformdata({
          id: 0,
          name: "",
          email: "",
          password: "",
        });
        getStudentList();
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  };

  const handleDelete = (id) => {
    fetch(`/student/${id}`, {
      method: "DELETE",
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to delete Student");
        }
      })
      .then((data) => {
        getStudentList();
      })
      .catch((error) => {
        console.error("Error deleting student:", error);
      });
  };

  const putInEditSection = (id, name, email, password) => {
    setEditForm({
      id: id,
      name: name,
      email: email,
      password: password,
    });
  };

  const handleEdit = (event) => {
    event.preventDefault();

    if (editForm.id === 0) {
      alert("No entry selected");
      return;
    } else if (
      editForm.name === "" ||
      editForm.email === "" ||
      editForm.password === ""
    ) {
      alert("Name or Email or Password is empty");
      return;
    }

    fetch(`/student/${editForm.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(editForm),
    })
      .then((res) => res.json())
      .then((data) => {
        getStudentList();
        setEditForm({
          id: 0,
          name: "",
          email: "",
          password: "",
        });
      })
      .catch((exc) => {
        console.log("Error: " + exc);
      });
  };

  return (
    <div className="student-mgmt-container">
      <h1>Add a new Student</h1>
      <form onSubmit={addStudent} className="student-form">
        <label htmlFor="Name">Name: </label>
        <input
          name="Name"
          type="text"
          value={formdata.name}
          onChange={(e) => setformdata({ ...formdata, name: e.target.value })}
        />

        <label htmlFor="Email">Email: </label>
        <input
          name="Email"
          type="email"
          value={formdata.email}
          onChange={(e) => setformdata({ ...formdata, email: e.target.value })}
        />

        <label htmlFor="Password">Password: </label>
        <input
          name="Password"
          type="text"
          value={formdata.password}
          onChange={(e) =>
            setformdata({ ...formdata, password: e.target.value })
          }
        />

        <button type="submit">Add Student</button>
      </form>

      <h1>List of Students</h1>
      <div className="student-list">
        {studentList.map((row) => (
          <div key={row.id} className="student-list-item">
            <p>
              {row.id} {row.name} {row.email} {row.password}
            </p>
            <div className="student-actions">
              <button
                onClick={() =>
                  putInEditSection(row.id, row.name, row.email, row.password)
                }
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
        onClick={() =>
          setEditForm({ id: 0, name: "", email: "", password: "" })
        }
      >
        Clear
      </button>
      <form onSubmit={handleEdit} className="student-form">
        <p>Id: {editForm.id}</p>

        <label htmlFor="editName">Edit Name: </label>
        <input
          name="editName"
          type="text"
          value={editForm.name}
          onChange={(e) => setEditForm({ ...editForm, name: e.target.value })}
        />

        <label htmlFor="editEmail">Edit Email: </label>
        <input
          name="editEmail"
          type="email"
          value={editForm.email}
          onChange={(e) => setEditForm({ ...editForm, email: e.target.value })}
        />

        <label htmlFor="editPassword">Edit Password: </label>
        <input
          name="editPassword"
          type="text"
          value={editForm.password}
          onChange={(e) =>
            setEditForm({ ...editForm, password: e.target.value })
          }
        />

        <button type="submit">Submit Edit</button>
      </form>
    </div>
  );
};

export default StudentMgmt;
