import React, { useState } from "react";
import { getExplanation } from "../services/api";

const ExplanationView = ({ incident }) => {
  const [explanation, setExplanation] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  if (!incident) return null;

  const fetchExplanation = () => {
    setLoading(true);
    setError(null);

    getExplanation(incident.id)
      .then((res) => setExplanation(res.data))
      .catch(() => setError("Failed to fetch explanation"))
      .finally(() => setLoading(false));
  };

  return (
    <div className="card">
      <button className="button" onClick={fetchExplanation}>
        Get Explanation
      </button>

      {loading && <p>Generating explanation...</p>}
      {error && <p className="error">{error}</p>}

      {explanation && (
        <div>
          <h3>AI Explanation</h3>
          <p><b>Summary:</b> {explanation.summary}</p>
          <p><b>Cause:</b> {explanation.probableCause}</p>
          <p><b>Recommendation:</b> {explanation.recommendation}</p>
        </div>
      )}
    </div>
  );
};

export default ExplanationView;