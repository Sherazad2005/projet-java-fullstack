import { Link } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";

export default function Navbar() {
    const { user, logout } = useAuth();
    const role = user?.role ?? user?.roles;

    return (
        <nav style={{ display: "flex", gap: 12, padding: 12, borderBottom: "1px solid #ddd" }}>
            <Link to="/">Home</Link>
            <Link to="/products">Produits</Link>
            {user && <Link to="/my-orders">Mes commandes</Link>}
            {role === "ADMIN" && (
                <>
                    <Link to="/admin/products">Admin Produits</Link>
                    <Link to="/admin/users">Admin Users</Link>
                </>
            )}
            <div style={{ marginLeft: "auto" }}>
                {!user ? (
                    <>
                        <Link to="/login">Login</Link>{" "}
                        <Link to="/register">Register</Link>
                    </>
                ) : (
                    <>
                        <span style={{ marginRight: 10 }}>{user.username ?? user.email} ({role})</span>
                        <button onClick={logout}>Logout</button>
                    </>
                )}
            </div>
        </nav>
    );
}
