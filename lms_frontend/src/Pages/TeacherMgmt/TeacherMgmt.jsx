import React, { useEffect, useState } from 'react'

const TeacherMgmt = () => {

    const [addData, setAddData] = useState({
        name: "",
        email: "",
        password: ""
    });

    const [teacherList, setTeacherList] = useState([]);

    const [editForm, setEditForm] = useState({
        id: 0,
        name: "",
        email: "",
        password: ""
    });

    const getTeacherList = () => {
        fetch("/teacher")
        .then(res => res.json())
        .then(data => {
            setTeacherList(data);
            console.log(data);
        }).catch(ex => {
            console.log("Error: " + ex);
        })
    }

    useEffect(() => {
        getTeacherList();
      }, []);

    const addTeacher = (event) => {
            event.preventDefault();

        if (addData.name === "" || addData.email === "" || addData.password === "") {
            alert("Name or Email or Password is empty");
            return;
        }

        fetch("/teacher", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(addData)
        }).then(res => res.json())
        .then(data => {
            setAddData({
                id: 0,
                name: "",
                email: "",
                password: ""
            })
            getTeacherList();
        })
        .catch(error => {
            console.error("Error: ", error);
        })
    }

    const handleDelete = (id) => {
        fetch(`/teacher/${id}`, {
            method: "DELETE",
        }).then(res => {
            if (!res.ok) {
                throw new Error("Failed to delete Teacher");
            }
        })
        .then((data) => {
            getTeacherList();
        }).catch(error => {
            console.error('Error deleting teacher:', error);
        });
    }

    const putInEditSection = (id, name, email, password) => {
        setEditForm({
            id: id,
            name: name,
            email: email,
            password: password
        })
    }

    const handleEdit = (event) => {
        event.preventDefault();

        if (editForm.id === 0) {
            alert("no entry selected");
            return;
        }
        else if (editForm.name === "" || editForm.email === "" || editForm.password === "") {
            alert("Name or Email or Password is empty");
            return;
        }

        fetch(`/teacher/${editForm.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(editForm)
        }).then(res => res.json())
        .then((data) => {
        getTeacherList();
        setEditForm({
            name: "",
            email: "",
            password: ""
        });
        }).catch((exc) => {
            console.log("Error: " + exc);
        })
    }

    return (
        <div>
            <h1>Add a new Teacher</h1>
            <form onSubmit={addTeacher}>
                <label htmlFor="Name" >Name: </label>
                <input name="Name" 
                type="text" 
                value={addData.name} 
                onChange={e => setAddData({...addData, name: e.target.value})} />

                <label htmlFor="Email">Email: </label>
                <input name="Email" 
                type="email" 
                value={addData.email} 
                onChange={e => setAddData({...addData, email: e.target.value})} />

                <label htmlFor="Password">Password: </label>
                <input name="Password" 
                type="text" 
                value={addData.password} 
                onChange={e => setAddData({...addData, password: e.target.value})} />  

                <button type="submit">Add Teacher</button>

            </form>

            <h1>List of Teachers</h1>
            {teacherList.length > 0 && teacherList.map((row) => {
                return <>
                <p>{row.id} {row.name} {row.email} {row.password}</p>
                <button onClick={() => handleDelete(row.id)}>delete</button>
                <button onClick={() => putInEditSection(row.id, row.name, row.email, row.password)}>edit</button>
                </>
            })}

            <h1>Edit Section</h1>
            <button onClick={() => setEditForm({id: 0, name: "", email: "", password: ""})}>Clear</button>
            <form onSubmit={handleEdit}>
                <p>Id: {editForm.id}</p>
                
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

                <label htmlFor="editPassword">Edit Password: </label>
                <input name="editPassword" 
                type="text" 
                value={editForm.password} 
                onChange={e => setEditForm({...editForm, password: e.target.value})}/>

                <button type="submit">Submit Edit</button>
            </form>
        </div>
    )
}

export default TeacherMgmt
