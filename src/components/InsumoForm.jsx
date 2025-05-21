import React, { useState } from 'react';

export default function InsumoForm({ initialData = {}, onSubmit }) {
  const [formData, setFormData] = useState({
    nombre: '',
    registroInvima: '',
    lugarAlmacenamiento: '',
    cantidad: 0,
    fechaRegistro: '',
    fechaApertura: '',
    fechaFinalizacion: '',
    fechaVencimiento: '',
    lote: '',
    fabricante: '',
    ...initialData
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>Nombre: <input name="nombre" value={formData.nombre} onChange={handleChange} required /></label>
      <label>Registro INVIMA: <input name="registroInvima" value={formData.registroInvima} onChange={handleChange} required /></label>
      <label>Lugar de almacenamiento: <input name="lugarAlmacenamiento" value={formData.lugarAlmacenamiento} onChange={handleChange} /></label>
      <label>Cantidad: <input type="number" name="cantidad" value={formData.cantidad} onChange={handleChange} min="0" required /></label>
      <label>Fecha de registro: <input type="date" name="fechaRegistro" value={formData.fechaRegistro} onChange={handleChange} /></label>
      <label>Fecha de apertura: <input type="date" name="fechaApertura" value={formData.fechaApertura} onChange={handleChange} /></label>
      <label>Fecha de finalización: <input type="date" name="fechaFinalizacion" value={formData.fechaFinalizacion} onChange={handleChange} /></label>
      <label>Fecha de vencimiento: <input type="date" name="fechaVencimiento" value={formData.fechaVencimiento} onChange={handleChange} required /></label>
      <label>Lote: <input name="lote" value={formData.lote} onChange={handleChange} required /></label>
      <label>Fabricante: <input name="fabricante" value={formData.fabricante} onChange={handleChange} required /></label>

      <button type="submit">Guardar</button>
    </form>
  );
}
