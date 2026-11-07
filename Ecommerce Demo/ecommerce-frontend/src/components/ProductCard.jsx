import { Link } from "react-router-dom";
import { formatPrice } from "../utils/format";
import useAddToCart from "../hooks/useAddToCart";

export default function ProductCard({ product }) {
  const { addToCart, status, loading } = useAddToCart();
  const outOfStock = product.stock <= 0;

  return (
    <div className="product-card">
      <Link to={`/product/${product.id}`}>
        <img src={product.imageUrl} alt={product.name} className="product-img" />
      </Link>

      <div className="product-body">
        {product.category && <span className="tag">{product.category.name}</span>}

        <Link to={`/product/${product.id}`} className="product-name">
          {product.name}
        </Link>

        <div className="product-price">{formatPrice(product.price)}</div>

        {outOfStock ? (
          <span className="stock out">Out of stock</span>
        ) : product.stock <= 10 ? (
          <span className="stock low">Only {product.stock} left</span>
        ) : (
          <span className="stock ok">In stock</span>
        )}

        <button
          className="btn btn-primary btn-full"
          disabled={outOfStock || loading}
          onClick={() => addToCart(product.id, 1)}
        >
          {loading ? "Adding..." : "Add to Cart"}
        </button>

        {status.text && (
          <div className={status.type === "success" ? "msg-success" : "msg-error"}>
            {status.text}
          </div>
        )}
      </div>
    </div>
  );
}