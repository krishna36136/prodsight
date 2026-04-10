import React, { useEffect, useState } from "react";
import { getLogs } from "../services/api";

const LogsView = ({ incident }) => {
  const [logs, setLogs] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!incident) return;

    setLoading(true);
    setError(null);

    getLogs(incident.id)
      .then((res) => setLogs(res.data))
      .catch(() => setError("Failed to load logs"))
      .finally(() => setLoading(false));
  }, [incident]);

  if (!incident) return <div className="card">Select an incident</div>;

  return (
    <div className="card">
      <h3>Logs ({incident.serviceName})</h3>

      {loading && <p>Loading logs...</p>}
      {error && <p className="error">{error}</p>}

      {!loading &&
        logs.map((log) => (
          <div key={log.id} className="list-item">
            [{log.level}] {log.message}
          </div>
        ))}

      {!loading && logs.length === 0 && <p>No logs found</p>}
    </div>
  );
};

export default LogsView;