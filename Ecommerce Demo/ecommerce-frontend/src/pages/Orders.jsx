import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api";
import { useAuth } from "../context/AuthContext";
import { formatPrice } from "../utils/format";

export default function Orders() {
  const { user } = useAuth();
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    api.get(`/orders/user/${user.id}`)
      .then((res) => setOrders(res.data))
      .catch(() => setError("Could not load your orders"))
      .finally(() => setLoading(false));
  }, [user.id]);

  if (loading) return <p className="muted center">Loading...</p>;
  if (error) return <div className="error-box">{error}</div>;

  if (orders.length === 0) {
    return (
      <div className="empty-state">
        <h2>No orders yet</h2>
        <p className="muted">When you place an order it will show up here.</p>
        <Link to="/" className="btn btn-primary">Start shopping</Link>
      </div>
    );
  }

  return (
    <div>
      <h2 className="page-title">My Orders</h2>

      {orders.map((order) => (
        <div className="order-card" key={order.id}>
          <div className="order-head">
            <div>
              <strong>Order #{order.id}</strong>
              <div className="muted">
                {new Date(order.createdAt).toLocaleString("en-IN")}
              </div>
            </div>
            <span className="status">{order.status}</span>
          </div>

          <div className="order-items">
            {order.items.map((item) => (
              <div className="summary-row" key={item.id}>
                <span>{item.productName} × {item.quantity}</span>
                <span>{formatPrice(Number(item.price) * item.quantity)}</span>
              </div>
            ))}
          </div>

          <div className="order-foot">
            <span className="muted">Ship to: {order.shippingAddress}</span>
            <strong>Total: {formatPrice(order.totalAmount)}</strong>
          </div>
        </div>
      ))}
    </div>
  );
}