import React, { useEffect, useState } from "react";
import { getIncidents } from "../services/api";

const IncidentList = ({ onSelect }) => {
  const [incidents, setIncidents] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchIncidents = () => {
    setLoading(true);
    setError(null);

    getIncidents(page, 5)
      .then((res) => {
        setIncidents(res.data.content);
        setTotalPages(res.data.totalPages);
      })
      .catch(() => setError("Failed to load incidents"))
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    fetchIncidents();
  }, [page]);

  return (
    <div className="card">
      <h2>Incidents</h2>

      {loading && <p>Loading incidents...</p>}
      {error && <p className="error">{error}</p>}

      {!loading &&
        incidents.map((incident) => (
          <div
            key={incident.id}
            className="list-item"
            onClick={() => onSelect(incident)}
          >
            {incident.serviceName} | {incident.severity}
          </div>
        ))}

      <div style={{ marginTop: "10px" }}>
        <button
          className="button"
          disabled={page === 0}
          onClick={() => setPage(page - 1)}
        >
          Prev
        </button>

        <span style={{ margin: "0 10px" }}>
          Page {page + 1} / {totalPages}
        </span>

        <button
          className="button"
          disabled={page === totalPages - 1}
          onClick={() => setPage(page + 1)}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default IncidentList;