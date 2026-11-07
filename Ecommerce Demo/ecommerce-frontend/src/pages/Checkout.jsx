import { useState } from "react";
import { Link } from "react-router-dom";
import api from "../api";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";
import { formatPrice } from "../utils/format";

export default function Checkout() {
  const { user } = useAuth();
  const { items, cartTotal, refreshCart } = useCart();
  const [address, setAddress] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [placedOrder, setPlacedOrder] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await api.post("/orders/checkout", {
        userId: user.id,
        shippingAddress: address,
      });
      setPlacedOrder(res.data);
      await refreshCart();
    } catch (err) {
      setError(err.response?.data?.message || "Could not place the order");
    } finally {
      setLoading(false);
    }
  };

  if (placedOrder) {
    return (
      <div className="empty-state">
        <h2>🎉 Order placed successfully!</h2>
        <p className="muted">
          Order #{placedOrder.id} · Total {formatPrice(placedOrder.totalAmount)}
        </p>
        <div className="btn-row">
          <Link to="/orders" className="btn btn-primary">View my orders</Link>
          <Link to="/" className="btn btn-outline">Continue shopping</Link>
        </div>
      </div>
    );
  }

  if (items.length === 0) {
    return (
      <div className="empty-state">
        <h2>Your cart is empty</h2>
        <Link to="/" className="btn btn-primary">Go shopping</Link>
      </div>
    );
  }

  return (
    <div>
      <h2 className="page-title">Checkout</h2>

      <div className="cart-layout">
        <form className="checkout-form" onSubmit={handleSubmit}>
          <h3>Shipping address</h3>
          {error && <div className="error-box">{error}</div>}

          <textarea
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            placeholder="House no, street, city, state, pincode"
            rows={4}
            required
          />

          <p className="muted">Payment: Cash on Delivery</p>

          <button className="btn btn-primary btn-full" disabled={loading}>
            {loading ? "Placing order..." : "Place Order"}
          </button>
        </form>

        <div className="summary-card">
          <h3>Your items</h3>
          {items.map((item) => (
            <div className="summary-row" key={item.id}>
              <span>{item.product.name} × {item.quantity}</span>
              <span>{formatPrice(Number(item.product.price) * item.quantity)}</span>
            </div>
          ))}
          <div className="summary-row total">
            <span>Total</span>
            <span>{formatPrice(cartTotal)}</span>
          </div>
        </div>
      </div>
    </div>
  );
}