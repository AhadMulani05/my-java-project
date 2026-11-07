import { useEffect, useState } from "react";
import api from "../api";
import { formatPrice } from "../utils/format";

const emptyForm = {
  name: "",
  description: "",
  price: "",
  stock: "",
  imageUrl: "",
  categoryId: "",
};

export default function Admin() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [newCategory, setNewCategory] = useState("");
  const [error, setError] = useState("");
  const [message, setMessage] = useState("");

  const loadData = async () => {
    try {
      const [p, c] = await Promise.all([api.get("/products"), api.get("/categories")]);
      setProducts(p.data);
      setCategories(c.data);
    } catch {
      setError("Could not load data. Is the backend running?");
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const resetForm = () => {
    setForm(emptyForm);
    setEditingId(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    const payload = {
      name: form.name,
      description: form.description,
      price: Number(form.price),
      stock: Number(form.stock),
      imageUrl: form.imageUrl,
      category: form.categoryId ? { id: Number(form.categoryId) } : null,
    };

    try {
      if (editingId) {
        await api.put(`/products/${editingId}`, payload);
        setMessage("Product updated");
      } else {
        await api.post("/products", payload);
        setMessage("Product added");
      }
      resetForm();
      await loadData();
    } catch (err) {
      setError(err.response?.data?.message || "Could not save the product");
    }
  };

  const startEdit = (p) => {
    setEditingId(p.id);
    setForm({
      name: p.name,
      description: p.description || "",
      price: p.price,
      stock: p.stock,
      imageUrl: p.imageUrl || "",
      categoryId: p.category ? p.category.id : "",
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDelete = async (p) => {
    if (!window.confirm(`Delete "${p.name}"?`)) return;
    setError("");
    setMessage("");
    try {
      await api.delete(`/products/${p.id}`);
      setMessage("Product deleted");
      await loadData();
    } catch {
      setError("Could not delete the product");
    }
  };

  const handleAddCategory = async (e) => {
    e.preventDefault();
    if (!newCategory.trim()) return;
    setError("");
    setMessage("");
    try {
      await api.post("/categories", { name: newCategory.trim() });
      setNewCategory("");
      setMessage("Category added");
      await loadData();
    } catch {
      setError("Could not add the category (the name may already exist)");
    }
  };

  return (
    <div>
      <h2 className="page-title">Admin Panel</h2>

      {error && <div className="error-box">{error}</div>}
      {message && <div className="success-box">{message}</div>}

      <form className="admin-form" onSubmit={handleSubmit}>
        <h3>{editingId ? "Edit product" : "Add new product"}</h3>

        <div className="form-grid">
          <div>
            <label>Name</label>
            <input name="name" value={form.name} onChange={handleChange} required />
          </div>

          <div>
            <label>Category</label>
            <select name="categoryId" value={form.categoryId} onChange={handleChange} required>
              <option value="">Select category</option>
              {categories.map((c) => (
                <option key={c.id} value={c.id}>{c.name}</option>
              ))}
            </select>
          </div>

          <div>
            <label>Price (₹)</label>
            <input type="number" step="0.01" min="0" name="price" value={form.price} onChange={handleChange} required />
          </div>

          <div>
            <label>Stock</label>
            <input type="number" min="0" name="stock" value={form.stock} onChange={handleChange} required />
          </div>

          <div className="span-2">
            <label>Image URL</label>
            <input name="imageUrl" value={form.imageUrl} onChange={handleChange} placeholder="https://..." />
          </div>

          <div className="span-2">
            <label>Description</label>
            <textarea name="description" rows={3} value={form.description} onChange={handleChange} />
          </div>
        </div>

        <div className="btn-row">
          <button className="btn btn-primary">{editingId ? "Update product" : "Add product"}</button>
          {editingId && (
            <button type="button" className="btn btn-outline" onClick={resetForm}>
              Cancel
            </button>
          )}
        </div>
      </form>

      <form className="admin-form inline-form" onSubmit={handleAddCategory}>
        <h3>Add category</h3>
        <div className="btn-row">
          <input
            value={newCategory}
            onChange={(e) => setNewCategory(e.target.value)}
            placeholder="New category name"
          />
          <button className="btn btn-primary">Add</button>
        </div>
      </form>

      <h3 className="table-title">All products ({products.length})</h3>
      <div className="table-wrap">
        <table className="admin-table">
          <thead>
            <tr>
              <th>Image</th>
              <th>Name</th>
              <th>Category</th>
              <th>Price</th>
              <th>Stock</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {products.map((p) => (
              <tr key={p.id}>
                <td><img src={p.imageUrl} alt="" /></td>
                <td>{p.name}</td>
                <td>{p.category ? p.category.name : "-"}</td>
                <td>{formatPrice(p.price)}</td>
                <td>{p.stock}</td>
                <td>
                  <button className="link-btn edit" onClick={() => startEdit(p)}>Edit</button>
                  <button className="link-btn" onClick={() => handleDelete(p)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}