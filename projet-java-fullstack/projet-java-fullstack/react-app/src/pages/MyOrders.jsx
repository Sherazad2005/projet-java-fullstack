import { useEffect, useState } from "react";
import api from "../api/axios";

export default function MyOrders() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        api.get("/api/orders/my-orders").then((r) => setOrders(r.data));
    }, []);

    return (
        <>
            <h1>Mes commandes</h1>
            <ul style={{ display: "grid", gap: 10, padding: 0, listStyle: "none" }}>
                {orders.map((o) => (
                    <li key={o.id} style={{ border: "1px solid #ddd", padding: 12 }}>
                        <div><b>Status:</b> {o.status}</div>
                        <div><b>Total:</b> {o.totalAmount}</div>
                        <div><b>Quantité:</b> {o.quantite}</div>
                    </li>
                ))}
            </ul>
        </>
    );
}
