import React, { useState } from 'react';
import axios from 'axios';

function EditarInsumo() {
  const [id, setId] = useState('');
  const [insumo, setInsumo] = useState({
    nombre: '',
    cantidad: '',
    lugarAlmacenamiento: '',
    registroInvima: '',
    lote: '',
    fabricante: '',
    fechaApertura: '',
    fechaFinalizacion: '',
    fechaVencimiento: ''
  });

  const handleChange = (e) => {
    setInsumo({
      ...insumo,
      [e.target.name]: e.target.value
    });
  };

const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    await axios.put(`http://localhost:9090/insumos/nombre/${insumo.nombre}`, insumo);
    alert('Insumo actualizado correctamente');
  } catch (error) {
    console.error('Error al actualizar el insumo', error);
    alert('Hubo un error al actualizar el insumo');
  }
};


  return (
    <div style={{ padding: '1rem' }}>
      <h2>Editar Insumo</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="id"
          placeholder="ID del Insumo"
          value={id}
          onChange={(e) => setId(e.target.value)}
          required
        /><br />

        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          value={insumo.nombre}
          onChange={handleChange}
          required
        /><br />

        <input
          type="number"
          name="cantidad"
          placeholder="Cantidad"
          value={insumo.cantidad}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="lugarAlmacenamiento"
          placeholder="Lugar de Almacenamiento"
          value={insumo.lugarAlmacenamiento}
          onChange={handleChange}
        /><br />

        <input
          type="text"
          name="registroInvima"
          placeholder="Registro INVIMA"
          value={insumo.registroInvima}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="lote"
          placeholder="Lote"
          value={insumo.lote}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="fabricante"
          placeholder="Fabricante"
          value={insumo.fabricante}
          onChange={handleChange}
          required
        /><br />

        <input
          type="date"
          name="fechaApertura"
          value={insumo.fechaApertura}
          onChange={handleChange}
        /><br />

        <input
          type="date"
          name="fechaFinalizacion"
          value={insumo.fechaFinalizacion}
          onChange={handleChange}
        /><br />

        <input
          type="date"
          name="fechaVencimiento"
          value={insumo.fechaVencimiento}
          onChange={handleChange}
          required
        /><br />

        <button type="submit">Actualizar Insumo</button>
      </form>
    </div>
  );
}

export default EditarInsumo;
