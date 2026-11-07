import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";
import { useAuth } from "../context/AuthContext";

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await api.post("/auth/login", form);
      login(res.data);
      navigate("/");
    } catch (err) {
      setError(err.response?.data?.message || "Could not connect to the server");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h2>Welcome back</h2>
        <p className="muted">Login to continue shopping</p>

        {error && <div className="error-box">{error}</div>}

        <label>Email</label>
        <input type="email" name="email" value={form.email} onChange={handleChange} placeholder="you@example.com" required />

        <label>Password</label>
        <input type="password" name="password" value={form.password} onChange={handleChange} placeholder="Your password" required />

        <button className="btn btn-primary btn-full" disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>

        <p className="muted center">
          New here? <Link to="/signup">Create an account</Link>
        </p>
      </form>
    </div>
  );
}