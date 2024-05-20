import { useEffect, useState } from "react"

const StudentMgmt = () => {

  const [formdata, setformdata] = useState({
    name: "",
    email: ""
  })

  const [studentList, setStudentList] = useState([]);

  const [editForm, setEditForm] = useState({
    student_id: 0,
    name: "",
    email: ""
  });

  const getStudentList = () => {
    fetch("/student")
    .then(res => res.json())
    .then(data => {
      setStudentList(data);
      console.log(data);
    }).catch((exc) => {
      console.log("Error: " + exc);
    })
  }

  useEffect(() => {
    getStudentList();
  }, []);

  const addStudent = (event) => {
    event.preventDefault();

    if (formdata.name === "" || formdata.email === "") {
      alert("Name or Email is empty");
      return;
    }

    fetch("/student", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formdata)
    }).then(res => res.json())
    .then(data => {
      setformdata({
        student_id: 0,
        name: "",
        email: ""
      })
      getStudentList();
    })
    .catch(error => {
      console.error("Error: ", error);
    })
  }

  const handleDelete = (student_id) => {
    
    fetch(`/student/${student_id}`, {
      method: "DELETE",
    }).then(res => {
      if (!res.ok) {
        throw new Error("Failed to delete Student");
      }
    })
    .then((data) => {
      getStudentList();
    }).catch(error => {
      console.error('Error deleting student:', error);
    });
  }

  const putInEditSection = (student_id, name, email) => {
    setEditForm({
      student_id: student_id,
      name: name,
      email: email
    })
  }

  const handleEdit = (event) => {
    event.preventDefault();

    if (editForm.student_id === 0) {
      alert("no entry selected");
      return;
    }
    else if (editForm.name === "" || editForm.email === "") {
      alert("Name or Email is empty");
      return;
    }

    fetch(`/student/${editForm.student_id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(editForm)
    }).then(res => res.json())
    .then((data) => {
      getStudentList();
      setEditForm({
        name: "",
        email: ""
      });
    }).catch((exc) => {
      console.log("Error: " + exc);
    })
  }

  return (
    <div>
      <h1>Add a new Student</h1>
      <form onSubmit={addStudent}>
        <label htmlFor="Name" >Name: </label>
        <input name="Name" 
        type="text" 
        value={formdata.name} 
        onChange={e => setformdata({...formdata, name: e.target.value})} />

        <label htmlFor="Email">Email: </label>
        <input name="Email" 
        type="email" 
        value={formdata.email} 
        onChange={e => setformdata({...formdata, email: e.target.value})} />

        <button type="submit">Add Student</button>
      </form>

      <h1>List of Students</h1>
      {studentList.map((row) => {
        return <>
          <p>{row.student_id} {row.name} {row.email}</p>
          <button onClick={() => handleDelete(row.student_id)}>delete</button>
          <button onClick={() => putInEditSection(row.student_id, row.name, row.email)}>edit</button>
        </>
      })}

      <h1>Edit Section</h1>
      <button onClick={() => setEditForm({student_id: 0, name: "", email: ""})}>Clear</button>
      <form onSubmit={handleEdit}>
        <p>Id: {editForm.student_id}</p>
        
        <label htmlFor="editName">Edit Name: </label>
        <input name="editName" 
        type="text" 
        value={editForm.name} 
        onChange={e => setEditForm({...editForm, name: e.target.value})}/>

        <label htmlFor="editEmail">Edit Email: </label>
        <input name="editEmail" 
        type="email" 
        value={editForm.email} 
        onChange={e => setEditForm({...editForm, email: e.target.value})}/>
        <button type="submit">Submit Edit</button>
      </form>
    </div>
  )
}

export default StudentMgmt
