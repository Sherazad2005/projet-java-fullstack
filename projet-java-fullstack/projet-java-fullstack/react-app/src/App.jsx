import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Products from "./pages/Products";
import ProductDetail from "./pages/ProductDetail";
import MyOrders from "./pages/MyOrders";
import AdminProducts from "./pages/AdminProducts";
import AdminUsers from "./pages/AdminUsers";
import RequireAuth from "./auth/RequireAuth";

export default function App() {
    return (
        <>
            <Navbar />
            <div style={{ padding: 16 }}>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/products" element={<Products />} />
                    <Route path="/products/:id" element={<ProductDetail />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    <Route
                        path="/my-orders"
                        element={
                            <RequireAuth>
                                <MyOrders />
                            </RequireAuth>
                        }
                    />

                    <Route
                        path="/admin/products"
                        element={
                            <RequireAuth role="ADMIN">
                                <AdminProducts />
                            </RequireAuth>
                        }
                    />
                    <Route
                        path="/admin/users"
                        element={
                            <RequireAuth role="ADMIN">
                                <AdminUsers />
                            </RequireAuth>
                        }
                    />
                </Routes>
            </div>
        </>
    );
}

