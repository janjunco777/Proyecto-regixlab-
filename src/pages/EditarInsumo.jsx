import React, { useState, useEffect } from 'react';

function EditarInsumo({ insumo, onGuardar }) {
  const [formData, setFormData] = useState({
    nombre: '',
    cantidad: 0,
    lugarAlmacenamiento: '',
    registroInvima: '',
    lote: '',
    fabricante: '',
    fechaApertura: '',
    fechaFinalizacion: '',
    fechaVencimiento: '',
  });

  useEffect(() => {
    if (insumo) {
      setFormData({
        nombre: insumo.nombre || '',
        cantidad: insumo.cantidad || 0,
        lugarAlmacenamiento: insumo.lugarAlmacenamiento || '',
        registroInvima: insumo.registroInvima || '',
        lote: insumo.lote || '',
        fabricante: insumo.fabricante || '',
        fechaApertura: insumo.fechaApertura || '',
        fechaFinalizacion: insumo.fechaFinalizacion || '',
        fechaVencimiento: insumo.fechaVencimiento || '',
      });
    }
  }, [insumo]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Aquí puedes llamar a tu backend con fetch o axios
    console.log('Datos a enviar:', formData);

    if (onGuardar) {
      onGuardar(formData); // función para guardar en el componente padre
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Editar Insumo</h3>

      <label>Nombre:</label>
      <input type="text" name="nombre" value={formData.nombre} onChange={handleChange} required />
      <br />

      <label>Cantidad:</label>
      <input type="number" name="cantidad" value={formData.cantidad} onChange={handleChange} min="0" required />
      <br />

      <label>Lugar de almacenamiento:</label>
      <input type="text" name="lugarAlmacenamiento" value={formData.lugarAlmacenamiento} onChange={handleChange} />
      <br />

      <label>Registro INVIMA:</label>
      <input type="text" name="registroInvima" value={formData.registroInvima} onChange={handleChange} required />
      <br />

      <label>Lote:</label>
      <input type="text" name="lote" value={formData.lote} onChange={handleChange} required />
      <br />

      <label>Fabricante:</label>
      <input type="text" name="fabricante" value={formData.fabricante} onChange={handleChange} required />
      <br />

      <label>Fecha de apertura:</label>
      <input type="date" name="fechaApertura" value={formData.fechaApertura} onChange={handleChange} />
      <br />

      <label>Fecha de finalización:</label>
      <input type="date" name="fechaFinalizacion" value={formData.fechaFinalizacion} onChange={handleChange} />
      <br />

      <label>Fecha de vencimiento:</label>
      <input type="date" name="fechaVencimiento" value={formData.fechaVencimiento} onChange={handleChange} required />
      <br />

      <button type="submit">Guardar cambios</button>
    </form>
  );
}

export default EditarInsumo;
