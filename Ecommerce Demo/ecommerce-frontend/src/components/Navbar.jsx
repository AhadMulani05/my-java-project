import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";

export default function Navbar() {
  const { user, logout } = useAuth();
  const { cartCount } = useCart();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <nav className="navbar">
      <Link to="/" className="brand">ShopEasy</Link>

      <div className="nav-links">
        <Link to="/">Home</Link>
        {user ? (
          <>
            {user.role === "ADMIN" && <Link to="/admin">Admin</Link>}

            <Link to="/orders">My Orders</Link>
            <Link to="/cart" className="cart-link">
              Cart
              {cartCount > 0 && <span className="badge">{cartCount}</span>}
            </Link>
            <span className="greeting">Hi, {user.name}</span>
            <button className="btn btn-outline" onClick={handleLogout}>Logout</button>
          </>
        ) : (
          <>
            <Link to="/login">Login</Link>
            <Link to="/signup" className="btn btn-primary">Sign up</Link>
          </>
        )}
      </div>
    </nav>
  );
}