import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

export default function RequireAuth({ children, role }) {
    const { user } = useAuth();
    if (!user) return <Navigate to="/login" replace />;

    const userRole = user.role ?? user.roles; // selon ton backend
    if (role && userRole !== role) return <Navigate to="/" replace />;

    return children;
}
