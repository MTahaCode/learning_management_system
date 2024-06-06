import { useEffect, useState } from "react"

const CourseMgmt = () => {

    const [addData, setAddData] = useState({
        name: "",
        credits: 0
      })
    
      const [courseList, setCourseList] = useState([]);
    
      const [editForm, setEditForm] = useState({
        course_id: 0,
        name: "",
        credits: 0
      });
    
      const getCourseList = () => {
        fetch("/course")
        .then(res => res.json())
        .then(data => {
          setCourseList(data);
          console.log(data);
        }).catch((exc) => {
          console.log("Error: " + exc);
        })
      }
    
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
            "Content-Type": "application/json"
          },
          body: JSON.stringify(addData)
        }).then(res => res.json())
        .then(data => {
          setAddData({
            name: "",
            credits: 0
          })
          getCourseList();
        })
        .catch(error => {
          console.error("Error: ", error);
        })
      }
    
      const handleDelete = (course_id) => {
        
        fetch(`/course/${course_id}`, {
          method: "DELETE",
        }).then(res => {
          if (!res.ok) {
            throw new Error("Failed to delete Course");
          }
        })
        .then((data) => {
          getCourseList();
        }).catch(error => {
          console.error('Error deleting course:', error);
        });
      }
    
      const putInEditSection = (course_id, name, credits) => {
        setEditForm({
          course_id: course_id,
          name: name,
          credits: credits
        })
      }
    
      const handleEdit = (event) => {
        event.preventDefault();
    
        if (editForm.course_id === 0) {
          alert("no entry selected");
          return;
        }
        else if (editForm.name === "" || editForm.credits === 0) {
          alert("Name or Credits is empty");
          return;
        }
    
        fetch(`/course/${editForm.course_id}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(editForm)
        }).then(res => res.json())
        .then((data) => {
          getCourseList();
          setEditForm({
            course_id: 0,
            name: "",
            credits: 0
          });
        }).catch((exc) => {
          console.log("Error: " + exc);
        })
      }
    
      return (
        <div>
          <h1>Add a new Course</h1>
          <form onSubmit={addCourse}>
            <label htmlFor="Name" >Name: </label>
            <input name="Name" 
            type="text" 
            value={addData.name} 
            onChange={e => setAddData({...addData, name: e.target.value})} />
    
            <label htmlFor="Credits">Credits: </label>
            <input name="Credits" 
            type="number" 
            value={addData.credits} 
            onChange={e => setAddData({...addData, credits: e.target.value})} />
    
            <button type="submit">Add Course</button>
          </form>f
    
          <h1>List of Courses</h1>
          {courseList.map((row) => {
            return <>
              <p>{row.id} {row.name} {row.credits}</p>
              <button onClick={() => handleDelete(row.course_id)}>delete</button>
              <button onClick={() => putInEditSection(row.course_id, row.name, row.credits)}>edit</button>
            </>
          })}
    
          <h1>Edit Section</h1>
          <button onClick={() => setEditForm({course_id: 0, name: "", credits: 0})}>Clear</button>
          <form onSubmit={handleEdit}>
            <p>Id: {editForm.course_id}</p>
            
            <label htmlFor="editName">Edit Name: </label>
            <input name="editName" 
            type="text" 
            value={editForm.name} 
            onChange={e => setEditForm({...editForm, name: e.target.value})}/>
    
            <label htmlFor="editCredits">Edit Credits: </label>
            <input name="editCredits" 
            type="number" 
            value={editForm.credits} 
            onChange={e => setEditForm({...editForm, credits: e.target.value})}/>
            <button type="submit">Submit Edit</button>
          </form>
        </div>
      )
}

export default CourseMgmt
