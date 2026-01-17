import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import HomePage from './pages/HomePage';
import './App.css';

/**
 * Main App Component
 * Sets up routing and global layout
 */
const App: React.FC = () => {
  return (
    <Router>
      <div className="app">
        <Header />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<HomePage />} />
          </Routes>
        </main>
        <footer className="footer">
          <p>© 2026 Beauty With Rumi. Made by Rukaiya Rahim Rumi.</p>
        </footer>
      </div>
    </Router>
  );
};

export default App;
