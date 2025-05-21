import axios from 'axios';

const API = 'http://localhost:8080/api/insumos';

export const registrarInsumo = (insumo) => axios.post(API, insumo);
export const editarInsumo = (id, insumo) => axios.put(`${API}/${id}`, insumo);
export const eliminarInsumo = (id) => axios.delete(`${API}/${id}`);
export const obtenerInsumos = () => axios.get(API);
export const obtenerHistorial = (id) => axios.get(`${API}/${id}/historial`);
