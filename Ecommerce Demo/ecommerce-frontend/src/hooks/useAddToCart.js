import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";

export default function useAddToCart() {
  const { user } = useAuth();
  const { refreshCart } = useCart();
  const navigate = useNavigate();
  const [status, setStatus] = useState({ type: "", text: "" });
  const [loading, setLoading] = useState(false);

  const addToCart = async (productId, quantity = 1) => {
    if (!user) {
      navigate("/login");
      return;
    }
    setLoading(true);
    setStatus({ type: "", text: "" });
    try {
      await api.post("/cart/add", { userId: user.id, productId, quantity });
      await refreshCart();
      setStatus({ type: "success", text: "Added to cart" });
    } catch (err) {
      setStatus({
        type: "error",
        text: err.response?.data?.message || "Could not add to cart",
      });
    } finally {
      setLoading(false);
    }
  };

  return { addToCart, status, loading };
}