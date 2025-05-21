import React from 'react';
import InsumoForm from '../components/InsumoForm';
import { registrarInsumo } from '../services/insumoService';

export default function RegistrarInsumo() {
  const handleSubmit = async (data) => {
    await registrarInsumo(data);
    alert('Insumo registrado correctamente');
  };

  return (
    <div>
      <h2>Registrar Insumo</h2>
      <InsumoForm onSubmit={handleSubmit} />
    </div>
  );
}
