import React from 'react';
import './Header.css';

/**
 * Header Component
 * Displays the app logo/title with girly styling
 */
const Header: React.FC = () => {
  return (
    <header className="header">
      <div className="header-container">
        <h1 className="logo">
          <span className="logo-beauty">Beauty</span>
          <span className="logo-with"> With </span>
          <span className="logo-rumi">Rumi</span>
        </h1>
        <p className="tagline">✨ Your Beauty Destination ✨</p>
      </div>
    </header>
  );
};

export default Header;
