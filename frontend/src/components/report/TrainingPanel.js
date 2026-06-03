import React from "react";
import {Container, Row, Col} from "reactstrap";
import Training from "./Training";
import ShelterReportInPdfButton from "./ShelterReportInPdfButton";
import axios from "axios";
import ShelterReportInCsvButton from "./ShelterReportInCsvButton";
import Config from "../../Config";

class TrainingPanel extends React.Component {
    state = {
        isLoading: true,
        error: null,
        reportObj: null
    };

    componentDidMount() {
        axios.get(`${Config.AJAX_BASE_URL}/trainings`)
            .then(res => this.setState({
                isLoading: false,
                reportObj: res.data
            }))
            .catch(err => this.setState({
                isLoading: false,
                error: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem"
            }));
    }

    render() {
        if(this.state.isLoading || this.state.error !== null) {
            return (
                <div style={{border: "4px black solid"}}>
                    <Container className="text-center">
                        <Row>
                            <Col>
                                <h1>{this.state.error !== null ?
                                    this.state.error : "Ładowanie raportu..."}</h1>
                            </Col>
                        </Row>
                    </Container>
                </div>
            );
        }

        return (
            <div style={{border: "4px black solid", paddingBottom: "10px"}}>
                <Container className="text-center">
                    <Row>
                        <Col>
                            <Training report={this.state.reportObj} privileged={this.props.privileged} onDelete={this.props.onDelete} />
                        </Col>
                    </Row>
                    <Row>
                        <Col xs="6">
                            <ShelterReportInPdfButton />
                        </Col>
                        <Col xs="6">
                            <ShelterReportInCsvButton />
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default TrainingPanel;
