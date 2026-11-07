import { createContext, useCallback, useContext, useEffect, useState } from "react";
import api from "../api";
import { useAuth } from "./AuthContext";

const CartContext = createContext(null);

export function CartProvider({ children }) {
  const { user } = useAuth();
  const [items, setItems] = useState([]);

  const refreshCart = useCallback(async () => {
    if (!user) {
      setItems([]);
      return;
    }
    try {
      const res = await api.get(`/cart/${user.id}`);
      setItems(res.data);
    } catch {
      // ignore, the cart just stays as it was
    }
  }, [user]);

  useEffect(() => {
    refreshCart();
  }, [refreshCart]);

  const cartCount = items.reduce((sum, i) => sum + i.quantity, 0);
  const cartTotal = items.reduce((sum, i) => sum + Number(i.product.price) * i.quantity, 0);

  return (
    <CartContext.Provider value={{ items, cartCount, cartTotal, refreshCart }}>
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  return useContext(CartContext);
}