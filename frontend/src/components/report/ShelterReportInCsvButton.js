import React from "react";
import Config from "../../Config";

class ShelterReportInCsvButton extends React.Component {
    render() {
        return (
            <div>
                <a className="btn btn-primary" href={`${Config.AJAX_BASE_URL}/report?format=csv`} role="button">Pobierz raport w CSV</a>
            </div>
        );
    }
}

export default ShelterReportInCsvButton;
