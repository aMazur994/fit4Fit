import React from "react";
import Config from "../../Config";

class ShelterReportInPdfButton extends React.Component {
    render() {
        return (
            <div>
                <a className="btn btn-primary" href={`${Config.AJAX_BASE_URL}/report?format=pdf`} role="button">Pobierz raport w PDF</a>
            </div>
        );
    }
}

export default ShelterReportInPdfButton;
