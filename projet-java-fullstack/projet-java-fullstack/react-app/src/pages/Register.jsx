import { useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";

export default function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [msg, setMsg] = useState("");
    const [err, setErr] = useState("");
    const nav = useNavigate();

    const submit = async (e) => {
        e.preventDefault();
        setErr(""); setMsg("");
        try {
            await api.post("/api/auth/register", { username, email, password });
            setMsg("Compte créé. Tu peux te connecter.");
            setTimeout(() => nav("/login"), 600);
        } catch {
            setErr("Register failed (email/username déjà pris ?)");
        }
    };

    return (
        <div style={{ maxWidth: 420 }}>
            <h1>Register</h1>
            <form onSubmit={submit} style={{ display: "grid", gap: 10 }}>
                <input value={username} onChange={(e) => setUsername(e.target.value)} placeholder="username" />
                <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="email" />
                <input value={password} onChange={(e) => setPassword(e.target.value)} placeholder="password" type="password" />
                <button>Créer compte</button>
            </form>
            {msg && <p style={{ color: "green" }}>{msg}</p>}
            {err && <p style={{ color: "crimson" }}>{err}</p>}
        </div>
    );
}
