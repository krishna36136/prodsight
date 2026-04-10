import React, { useState } from "react";
import "./styles/styles.css";

import IncidentList from "./components/IncidentList";
import LogsView from "./components/LogsView";
import ExplanationView from "./components/ExplanationView";

function App() {
  const [selectedIncident, setSelectedIncident] = useState(null);

  return (
    <div className="container">
      <h1>ProdSight Dashboard</h1>

      <IncidentList onSelect={setSelectedIncident} />

      <LogsView incident={selectedIncident} />

      <ExplanationView incident={selectedIncident} />
    </div>
  );
}

export default App;