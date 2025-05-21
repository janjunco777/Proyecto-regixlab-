import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

import './App.css';
import Insumos from './components/Insumos';
import EliminarInsumo from './pages/EliminarInsumo';
import EditarInsumo from './components/EditarInsumo';


function App() {
  return (
    <Router>
      <div className="App">
        <nav style={{ padding: '1rem', background: '#eee' }}>
          <Link to="/">Inicio</Link> |{" "}
          <Link to="/insumos">Insumos</Link> |{" "}
          <Link to="/eliminar">Eliminar Insumo</Link>
          <Link to="/editar">Editar Insumo</Link>
        </nav>

        <Routes>
          <Route path="/" element={<h2>Bienvenido al sistema de gestión de insumos</h2>} />
          <Route path="/insumos" element={<Insumos />} />
          <Route path="/eliminar" element={<EliminarInsumo />} />
          <Route path="/editar" element={<EditarInsumo />} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;

