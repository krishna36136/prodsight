import axios from "axios";

const INCIDENT_API = "http://localhost:8085";
const AI_API = "http://localhost:8086";

export const getIncidents = (page, size) =>
  axios.get(`${INCIDENT_API}/incidents?page=${page}&size=${size}`);

export const getLogs = (id) =>
  axios.get(`${INCIDENT_API}/incidents/${id}/logs`);

export const getExplanation = (id) =>
  axios.get(`${AI_API}/explanations/${id}`);