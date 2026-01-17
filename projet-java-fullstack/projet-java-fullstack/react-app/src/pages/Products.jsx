import { useEffect, useState } from "react";
import api from "../api/axios";
import { Link } from "react-router-dom";

export default function Products() {
    const [items, setItems] = useState([]);
    const [q, setQ] = useState("");
    const [page, setPage] = useState(0);
    const [err, setErr] = useState("");

    const load = async () => {
        setErr("");
        try {
            const res = await api.get("/api/products", { params: { page, size: 10 } });
            console.log("API /api/products =", res.data);
            setItems(res.data.content ?? res.data);
        } catch (e) {
            console.error("API ERROR:", e);
            setErr("Erreur API: /api/products (regarde Console + Network)");
            setItems([]);
        }
    };

    const search = async () => {
        setErr("");
        try {
            const res = await api.get("/api/products/search", {
                params: { name: q, page: 0, size: 10 },
            });
            console.log("API /api/products/search =", res.data);
            setItems(res.data.content ?? res.data);
            setPage(0);
        } catch (e) {
            console.error("SEARCH ERROR:", e);
            setErr("Erreur API: /api/products/search");
            setItems([]);
        }
    };

    useEffect(() => {
        load();
    }, [page]);

    return (
        <>
            <h1>Produits</h1>
            {err && <p style={{ color: "crimson" }}>{err}</p>}

            <div style={{ display: "flex", gap: 10, marginBottom: 12 }}>
                <input value={q} onChange={(e) => setQ(e.target.value)} placeholder="recherche nom..." />
                <button onClick={search}>Rechercher</button>
                <button onClick={load}>Reset</button>
            </div>

            <ul style={{ display: "grid", gap: 10, padding: 0, listStyle: "none" }}>
                {items.map((p) => (
                    <li key={p.id} style={{ border: "1px solid #ddd", padding: 12 }}>
                        <b>{p.name}</b> — {p.price} €
                        <div>
                            <Link to={`/products/${p.id}`}>Voir détail</Link>
                        </div>
                    </li>
                ))}
            </ul>

            <div style={{ marginTop: 12, display: "flex", gap: 10 }}>
                <button disabled={page === 0} onClick={() => setPage((x) => x - 1)}>
                    Prev
                </button>
                <button onClick={() => setPage((x) => x + 1)}>Next</button>
            </div>
        </>
    );
}

