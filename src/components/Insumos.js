// src/components/Insumos.js
import React, { useState, useEffect } from 'react';

const Insumos = () => {
  const [insumos, setInsumos] = useState([]);
  const [nombreBusqueda, setNombreBusqueda] = useState('');
  const [mensaje, setMensaje] = useState('');

  const backendUrl = 'http://localhost:9090/api/insumos'; // Cambia según tu backend

  // Obtener todos los insumos
  useEffect(() => {
    fetch(backendUrl)
      .then(res => res.json())
      .then(data => setInsumos(data))
      .catch(err => console.error(err));
  }, []);

  // Eliminar por nombre
  const eliminarPorNombre = () => {
    fetch(`${backendUrl}/nombre/${nombreBusqueda}`, {
      method: 'DELETE',
    })
      .then(res => {
        if (res.ok) {
          setMensaje(`Insumo "${nombreBusqueda}" eliminado exitosamente`);
          // Actualizar lista
          setInsumos(insumos.filter(i => i.nombre !== nombreBusqueda));
        } else {
          setMensaje('Error al eliminar el insumo');
        }
      })
      .catch(err => {
        console.error(err);
        setMensaje('Error de conexión con el backend');
      });
  };

  return (
    <div>
      <h2>Listado de Insumos</h2>
      <ul>
        {insumos.map((insumo) => (
          <li key={insumo.id}>{insumo.nombre} - {insumo.cantidad}</li>
        ))}
      </ul>

      <h3>Eliminar Insumo por Nombre</h3>
      <input
        type="text"
        value={nombreBusqueda}
        onChange={(e) => setNombreBusqueda(e.target.value)}
        placeholder="Nombre del insumo"
      />
      <button onClick={eliminarPorNombre}>Eliminar</button>

      {mensaje && <p>{mensaje}</p>}
    </div>
  );
};

export default Insumos;
