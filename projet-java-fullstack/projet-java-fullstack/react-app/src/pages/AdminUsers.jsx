import { useEffect, useState } from "react";
import api from "../api/axios";

export default function AdminUsers() {
    const [users, setUsers] = useState([]);

    const load = async () => {
        const res = await api.get("/api/admin/users");
        setUsers(res.data);
    };

    useEffect(() => { load(); }, []);

    const setRole = async (u, role) => {
        await api.put(`/api/admin/users/${u.id}`, { ...u, role, roles: role });
        load();
    };

    return (
        <>
            <h1>Admin - Users</h1>
            <ul style={{ display: "grid", gap: 10, padding: 0, listStyle: "none" }}>
                {users.map((u) => (
                    <li key={u.id} style={{ border: "1px solid #ddd", padding: 12 }}>
                        <div><b>{u.username}</b> — {u.email}</div>
                        <div>role: {u.role ?? u.roles}</div>
                        <button onClick={() => setRole(u, "USER")}>USER</button>
                        <button onClick={() => setRole(u, "ADMIN")}>ADMIN</button>
                    </li>
                ))}
            </ul>
        </>
    );
}
