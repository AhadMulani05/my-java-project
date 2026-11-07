import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import api from "../api";
import { formatPrice } from "../utils/format";
import useAddToCart from "../hooks/useAddToCart";

export default function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const { addToCart, status, loading: adding } = useAddToCart();

  useEffect(() => {
    setLoading(true);
    api.get(`/products/${id}`)
      .then((res) => setProduct(res.data))
      .catch(() => setError("Product not found"))
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) return <p className="muted center">Loading...</p>;
  if (error) return <div className="error-box">{error}</div>;

  const outOfStock = product.stock <= 0;

  return (
    <div>
      <Link to="/" className="back-link">← Back to products</Link>

      <div className="detail">
        <img src={product.imageUrl} alt={product.name} className="detail-img" />

        <div className="detail-info">
          {product.category && <span className="tag">{product.category.name}</span>}
          <h1>{product.name}</h1>
          <div className="detail-price">{formatPrice(product.price)}</div>
          <p className="detail-desc">{product.description}</p>

          {outOfStock ? (
            <span className="stock out">Out of stock</span>
          ) : (
            <span className="stock ok">{product.stock} available</span>
          )}

          {!outOfStock && (
            <div className="qty-row">
              <button
                className="qty-btn"
                onClick={() => setQuantity((q) => Math.max(1, q - 1))}
              >
                −
              </button>
              <span className="qty-value">{quantity}</span>
              <button
                className="qty-btn"
                onClick={() => setQuantity((q) => Math.min(product.stock, q + 1))}
              >
                +
              </button>
            </div>
          )}

          <button
            className="btn btn-primary detail-btn"
            disabled={outOfStock || adding}
            onClick={() => addToCart(product.id, quantity)}
          >
            {adding ? "Adding..." : "Add to Cart"}
          </button>

          {status.text && (
            <div className={status.type === "success" ? "msg-success" : "msg-error"}>
              {status.text}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}