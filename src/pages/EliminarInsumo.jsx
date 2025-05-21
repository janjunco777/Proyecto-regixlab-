import React, { useState } from 'react';

function EliminarInsumo() {
  const [nombre, setNombre] = useState('');
  const [mensaje, setMensaje] = useState('');

  const handleEliminar = async () => {
    if (!nombre) {
      setMensaje('Por favor, ingresa un nombre.');
      return;
    }

    try {
      const response = await fetch(`http://localhost:9090/api/insumos/por-nombre/${nombre}`, {
        method: 'DELETE',
      });

      const text = await response.text();

      if (response.ok) {
        setMensaje(`✅ ${text}`);
        setNombre('');
      } else {
        setMensaje(`❌ ${text}`);
      }
    } catch (error) {
      setMensaje('❌ Error al conectar con el servidor.');
    }
  };

  return (
    <div style={{ padding: '1rem', maxWidth: '400px', margin: 'auto' }}>
      <h2>Eliminar Insumo</h2>
      <input
        type="text"
        placeholder="Nombre del insumo"
        value={nombre}
        onChange={(e) => setNombre(e.target.value)}
        style={{ width: '100%', padding: '0.5rem', marginBottom: '1rem' }}
      />
      <button onClick={handleEliminar} style={{ padding: '0.5rem 1rem' }}>
        Eliminar
      </button>
      {mensaje && (
        <p style={{ marginTop: '1rem', color: mensaje.includes('✅') ? 'green' : 'red' }}>{mensaje}</p>
      )}
    </div>
  );
}

export default EliminarInsumo;
