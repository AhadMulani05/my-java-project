import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";
import { useCart } from "../context/CartContext";
import { formatPrice } from "../utils/format";

export default function Cart() {
  const { items, cartTotal, refreshCart } = useCart();
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const changeQty = async (item, newQty) => {
    setError("");
    try {
      await api.put(`/cart/update/${item.id}`, null, { params: { quantity: newQty } });
      await refreshCart();
    } catch (err) {
      setError(err.response?.data?.message || "Could not update the cart");
    }
  };

  const removeItem = async (item) => {
    setError("");
    try {
      await api.delete(`/cart/remove/${item.id}`);
      await refreshCart();
    } catch {
      setError("Could not remove the item");
    }
  };

  if (items.length === 0) {
    return (
      <div className="empty-state">
        <h2>Your cart is empty</h2>
        <p className="muted">Add some products to get started.</p>
        <Link to="/" className="btn btn-primary">Continue shopping</Link>
      </div>
    );
  }

  return (
    <div>
      <h2 className="page-title">Shopping Cart</h2>
      {error && <div className="error-box">{error}</div>}

      <div className="cart-layout">
        <div className="cart-list">
          {items.map((item) => (
            <div className="cart-item" key={item.id}>
              <img src={item.product.imageUrl} alt={item.product.name} />

              <div className="cart-info">
                <Link to={`/product/${item.product.id}`} className="product-name">
                  {item.product.name}
                </Link>
                <div className="muted">{formatPrice(item.product.price)} each</div>

                <div className="qty-row">
                  <button
                    className="qty-btn"
                    disabled={item.quantity <= 1}
                    onClick={() => changeQty(item, item.quantity - 1)}
                  >
                    −
                  </button>
                  <span className="qty-value">{item.quantity}</span>
                  <button
                    className="qty-btn"
                    onClick={() => changeQty(item, item.quantity + 1)}
                  >
                    +
                  </button>
                </div>
              </div>

              <div className="cart-right">
                <div className="line-total">
                  {formatPrice(Number(item.product.price) * item.quantity)}
                </div>
                <button className="link-btn" onClick={() => removeItem(item)}>
                  Remove
                </button>
              </div>
            </div>
          ))}
        </div>

        <div className="summary-card">
          <h3>Order Summary</h3>
          <div className="summary-row">
            <span>Items</span>
            <span>{items.reduce((s, i) => s + i.quantity, 0)}</span>
          </div>
          <div className="summary-row total">
            <span>Total</span>
            <span>{formatPrice(cartTotal)}</span>
          </div>
          <button className="btn btn-primary btn-full" onClick={() => navigate("/checkout")}>
            Proceed to Checkout
          </button>
        </div>
      </div>
    </div>
  );
}