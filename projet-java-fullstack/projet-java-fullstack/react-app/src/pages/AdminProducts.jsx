import { useEffect, useState } from "react";
import api from "../api/axios";

export default function AdminProducts() {
    const [items, setItems] = useState([]);
    const [form, setForm] = useState({ name: "", price: 10, description: "", stockQuantity: 0, lienImage: "" });

    const load = async () => {
        const res = await api.get("/api/products", { params: { page: 0, size: 50 } });
        setItems(res.data.content ?? res.data);
    };

    useEffect(() => { load(); }, []);

    const create = async () => {
        await api.post("/api/admin/products", form);
        setForm({ name: "", price: 10, description: "", stockQuantity: 0, lienImage: "" });
        load();
    };

    const del = async (id) => {
        if (!confirm("Supprimer ce produit ?")) return;
        await api.delete(`/api/admin/products/${id}`);
        load();
    };

    return (
        <>
            <h1>Admin - Produits</h1>

            <div style={{ border: "1px solid #ddd", padding: 12, marginBottom: 16 }}>
                <h3>Ajouter produit</h3>
                <input placeholder="name" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} />
                <input type="number" placeholder="price" value={form.price} onChange={(e) => setForm({ ...form, price: Number(e.target.value) })} />
                <input placeholder="image url" value={form.lienImage} onChange={(e) => setForm({ ...form, lienImage: e.target.value })} />
                <input type="number" placeholder="stock" value={form.stockQuantity} onChange={(e) => setForm({ ...form, stockQuantity: Number(e.target.value) })} />
                <textarea placeholder="description" value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} />
                <button onClick={create}>Créer</button>
            </div>

            <ul style={{ display: "grid", gap: 10, padding: 0, listStyle: "none" }}>
                {items.map((p) => (
                    <li key={p.id} style={{ border: "1px solid #ddd", padding: 12 }}>
                        <b>{p.name}</b> — {p.price} €
                        <div>stock: {p.stockQuantity}</div>
                        <button onClick={() => del(p.id)}>Supprimer</button>
                    </li>
                ))}
            </ul>
        </>
    );
}
