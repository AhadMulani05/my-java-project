import { useEffect, useState } from "react";
import api from "../api";
import ProductCard from "../components/ProductCard";

export default function Home() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [categoryId, setCategoryId] = useState(null);
  const [search, setSearch] = useState("");
  const [searchInput, setSearchInput] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Load categories once
  useEffect(() => {
    api.get("/categories")
      .then((res) => setCategories(res.data))
      .catch(() => {});
  }, []);

  // Load products whenever category or search changes
  useEffect(() => {
    const params = {};
    if (search) params.search = search;
    else if (categoryId) params.categoryId = categoryId;

    setLoading(true);
    setError("");
    api.get("/products", { params })
      .then((res) => setProducts(res.data))
      .catch(() => setError("Could not load products. Is the backend running?"))
      .finally(() => setLoading(false));
  }, [categoryId, search]);

  const handleSearch = (e) => {
    e.preventDefault();
    setCategoryId(null);
    setSearch(searchInput.trim());
  };

  const handleCategory = (id) => {
    setSearch("");
    setSearchInput("");
    setCategoryId(id);
  };

  return (
    <div>
      <div className="hero">
        <h1>Shop everything you love</h1>
        <p>Great products at great prices</p>
        <form className="search-bar" onSubmit={handleSearch}>
          <input
            value={searchInput}
            onChange={(e) => setSearchInput(e.target.value)}
            placeholder="Search products..."
          />
          <button className="btn btn-primary">Search</button>
        </form>
      </div>

      <div className="chips">
        <button
          className={`chip ${!categoryId && !search ? "active" : ""}`}
          onClick={() => handleCategory(null)}
        >
          All
        </button>
        {categories.map((c) => (
          <button
            key={c.id}
            className={`chip ${categoryId === c.id ? "active" : ""}`}
            onClick={() => handleCategory(c.id)}
          >
            {c.name}
          </button>
        ))}
      </div>

      {search && <p className="muted result-info">Results for "{search}"</p>}

      {loading && <p className="muted center">Loading products...</p>}
      {error && <div className="error-box">{error}</div>}
      {!loading && !error && products.length === 0 && (
        <p className="muted center">No products found.</p>
      )}

      <div className="product-grid">
        {products.map((p) => (
          <ProductCard key={p.id} product={p} />
        ))}
      </div>
    </div>
  );
}