import axios from 'axios';
import { useEffect, useState } from "react";
import './student.css';

function Student() {
    const [id, setId] = useState('');
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const [phone, setPhone] = useState("");
    const [students, setStudents] = useState([]);

    useEffect(() => {
        (async () => await loadStudents())();
    }, []);

    async function loadStudents() {
        try {
            const result = await axios.get("http://localhost:8080/student/getAll");
            setStudents(result.data);
        } catch (err) {
            alert("Failed to load students. Check your API.");
        }
    }

    async function save(event) {
        event.preventDefault();
        if (!name || !address || !phone) {
            alert("All fields are required.");
            return;
        }
        try {
            await axios.post("http://localhost:8080/student/save", { name, address, phone });
            alert("Student Registered Successfully!");
            resetForm();
            loadStudents();
        } catch (err) {
            alert("Student Registration Failed");
        }
    }

    async function editStudent(student) {
        setName(student.name);
        setAddress(student.address);
        setPhone(student.phone);
        setId(student._id);
    }

    async function deleteStudent(studentId) {
        try {
            await axios.delete(`http://localhost:8080/student/delete/${studentId}`);
            alert("Student Deleted Successfully!");
            loadStudents();
        } catch (err) {
            alert("Failed to delete student.");
        }
    }

    async function update(event) {
        event.preventDefault();
        if (!name || !address || !phone) {
            alert("All fields are required.");
            return;
        }
        try {
            await axios.put(`http://localhost:8080/student/edit/${id}`, { name, address, phone });
            alert("Student Updated Successfully!");
            resetForm();
            loadStudents();
        } catch (err) {
            alert("Student Update Failed.");
        }
    }

    function resetForm() {
        setId('');
        setName('');
        setAddress('');
        setPhone('');
    }

    return (
        <div className="container mt-5">
            <div className="header-section text-center">
                <h1 className="text-primary display-4 fw-bold">📘 Student Management</h1>
                <p className="text-secondary fs-5">Easily manage student records </p>
            </div>

            {/* Form Section */}
            <div className="card p-4 shadow border-0 mt-4">
                <h2 className="text-center text-secondary mb-4">Add / Update Student</h2>
                <form>
                    <div className="form-group mb-3">
                        <label className="form-label">Student Name</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter student name"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div className="form-group mb-3">
                        <label className="form-label">Student Address</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter student address"
                            value={address}
                            onChange={(e) => setAddress(e.target.value)}
                        />
                    </div>
                    <div className="form-group mb-3">
                        <label className="form-label">Phone Number</label>
                        <input
                            type="text"
                            className="form-control"
                            placeholder="Enter phone number"
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                        />
                    </div>
                    <div className="d-flex justify-content-center mt-3">
                        <button type="submit" className="btn btn-success me-3 px-4 m-2" onClick={save}>Register</button>
                        <button type="button" className="btn btn-warning px-4 m-2" onClick={update}>Update</button>
                    </div>
                </form>
            </div>

            {/* Student List Section */}
            <h2 className="mt-5 text-center text-primary">Registered Students</h2>
            <div className="card p-4 shadow border-0 mt-3">
                <div className="d-flex justify-content-between align-items-center mb-3">
                    <h5 className="text-secondary fw-bold">All Students</h5>
                    <input
                        type="text"
                        className="form-control w-25"
                        placeholder="Search by name..."
                        onChange={(e) => {
                            const searchTerm = e.target.value.toLowerCase();
                            setStudents((prevStudents) =>
                                prevStudents.filter((student) =>
                                    student.name.toLowerCase().includes(searchTerm)
                                )
                            );
                        }}
                    />
                </div>
                <table className="table table-hover text-center align-middle">
                    <thead className="table-dark">
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {students.map((student) => (
                            <tr key={student._id} className="table-light">
                                <td>{student.name}</td>
                                <td>{student.address}</td>
                                <td>{student.phone}</td>
                                <td>
                                    <button
                                        className="btn btn-sm btn-outline-primary me-2"
                                        onClick={() => editStudent(student)}
                                    >
                                        ✏️ Edit
                                    </button>
                                    <button
                                        className="btn btn-sm btn-outline-danger"
                                        onClick={() => deleteStudent(student._id)}
                                    >
                                        🗑️ Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                {students.length === 0 && (
                    <div className="alert alert-info text-center" role="alert">
                        No students found. Add new students to view here!
                    </div>
                )}
            </div>
        </div>
    );
}

export default Student;
