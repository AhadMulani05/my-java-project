import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";

export default function Signup() {
  const [form, setForm] = useState({ name: "", email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (form.password.length < 6) {
      setError("Password must be at least 6 characters");
      return;
    }

    setLoading(true);
    try {
      await api.post("/auth/signup", form);
      navigate("/login");
    } catch (err) {
      setError(err.response?.data?.message || "Could not connect to the server");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <form className="auth-card" onSubmit={handleSubmit}>
        <h2>Create your account</h2>
        <p className="muted">Join us and start shopping</p>

        {error && <div className="error-box">{error}</div>}

        <label>Full name</label>
        <input name="name" value={form.name} onChange={handleChange} placeholder="Your name" required />

        <label>Email</label>
        <input type="email" name="email" value={form.email} onChange={handleChange} placeholder="you@example.com" required />

        <label>Password</label>
        <input type="password" name="password" value={form.password} onChange={handleChange} placeholder="At least 6 characters" required />

        <button className="btn btn-primary btn-full" disabled={loading}>
          {loading ? "Creating account..." : "Sign up"}
        </button>

        <p className="muted center">
          Already have an account? <Link to="/login">Login</Link>
        </p>
      </form>
    </div>
  );
}