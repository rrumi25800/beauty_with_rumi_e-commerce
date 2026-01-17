import React from 'react';
import { BeautyProduct } from '../types/BeautyProduct';
import './ProductCard.css';

/**
 * ProductCard Component Props
 */
interface ProductCardProps {
  product: BeautyProduct;
  onEdit: (product: BeautyProduct) => void;
  onDelete: (id: string) => void;
  onToggleStock: (id: string, currentStock: boolean) => void;
}

/**
 * ProductCard Component
 * Displays individual product information in a card format
 */
const ProductCard: React.FC<ProductCardProps> = ({ 
  product, 
  onEdit, 
  onDelete,
  onToggleStock 
}) => {
  const handleDelete = () => {
    if (window.confirm(`Are you sure you want to delete "${product.name}"?`)) {
      onDelete(product.id!);
    }
  };

  const handleToggleStock = () => {
    onToggleStock(product.id!, product.inStock);
  };

  return (
    <div className={`product-card ${!product.inStock ? 'out-of-stock' : ''}`}>
      <div className="product-header">
        <h3 className="product-name">{product.name}</h3>
        <span className={`stock-badge ${product.inStock ? 'in-stock' : 'out-of-stock'}`}>
          {product.inStock ? '✓ In Stock' : '✗ Out of Stock'}
        </span>
      </div>

      <div className="product-info">
        <p className="product-brand">by {product.brand}</p>
        <span className="product-category">{product.category}</span>
      </div>

      <div className="product-details">
        {product.shade && (
          <div className="detail-item">
            <span className="detail-label">Shade:</span>
            <span className="detail-value">{product.shade}</span>
          </div>
        )}
        <div className="detail-item">
          <span className="detail-label">Skin Type:</span>
          <span className="detail-value">{product.skinType}</span>
        </div>
      </div>

      <p className="product-description">{product.description}</p>

      <div className="product-footer">
        <span className="product-price">${product.price.toFixed(2)}</span>
        <div className="product-actions">
          <button 
            className="btn btn-toggle"
            onClick={handleToggleStock}
            title="Toggle stock status"
          >
            📦
          </button>
          <button 
            className="btn btn-edit"
            onClick={() => onEdit(product)}
          >
            ✏️ Edit
          </button>
          <button 
            className="btn btn-delete"
            onClick={handleDelete}
          >
            🗑️ Delete
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
