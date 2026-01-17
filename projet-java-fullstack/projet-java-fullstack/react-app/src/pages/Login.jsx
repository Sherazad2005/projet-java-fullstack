import { useState } from "react";
import api from "../api/axios";
import { useAuth } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const [login, setLogin] = useState("");
    const [password, setPassword] = useState("");
    const [err, setErr] = useState("");
    const auth = useAuth();
    const nav = useNavigate();

    const submit = async (e) => {
        e.preventDefault();
        setErr("");
        try {
            const res = await api.post("/api/auth/login", { login, password });
            auth.login(res.data);
            nav("/");
        } catch (e) {
            setErr("Login failed. Vérifie backend / identifiants.");
        }
    };

    return (
        <div style={{ maxWidth: 420 }}>
            <h1>Login</h1>
            <form onSubmit={submit} style={{ display: "grid", gap: 10 }}>
                <input value={login} onChange={(e) => setLogin(e.target.value)} placeholder="username ou email" />
                <input value={password} onChange={(e) => setPassword(e.target.value)} placeholder="password" type="password" />
                <button>Se connecter</button>
            </form>
            {err && <p style={{ color: "crimson" }}>{err}</p>}
        </div>
    );
}
