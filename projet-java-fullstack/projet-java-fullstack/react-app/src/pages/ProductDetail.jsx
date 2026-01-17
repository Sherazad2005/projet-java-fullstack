import { useEffect, useState } from "react";
import api from "../api/axios";
import { useParams } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";

export default function ProductDetail() {
    const { id } = useParams();
    const { user } = useAuth();
    const [p, setP] = useState(null);
    const [qty, setQty] = useState(1);
    const [msg, setMsg] = useState("");

    useEffect(() => {
        api.get(`/api/products/${id}`).then((r) => setP(r.data));
    }, [id]);

    const order = async () => {
        setMsg("");
        try {
            await api.post("/api/orders", { productId: Number(id), quantite: Number(qty) });
            setMsg("Commande créée ✅");
        } catch {
            setMsg("Commande impossible (login requis ?)");
        }
    };

    if (!p) return <p>Loading...</p>;

    return (
        <>
            <h1>{p.name}</h1>
            <p>{p.description}</p>
            <p><b>Prix:</b> {p.price} €</p>

            {user && (
                <div style={{ display: "flex", gap: 10, alignItems: "center" }}>
                    <input type="number" min="1" value={qty} onChange={(e) => setQty(e.target.value)} />
                    <button onClick={order}>Commander</button>
                </div>
            )}

            {msg && <p>{msg}</p>}
            {!user && <p>Connecte-toi pour commander.</p>}
        </>
    );
}
