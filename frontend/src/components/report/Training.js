import React from "react";
import {Row, Col, CardColumns} from "reactstrap";
import Exercise from "./Exercise";

const Training = props => {
    const reportObj = props.training;

    return (
        <div>
            <Row>
                <Col>
                    <h2 className="text-center">Moje treningi</h2>
                    <br />
                    {reportObj.animals.length !== 0 ?
                        <CardColumns>
                            {reportObj.animals.map(animal => <Exercise key={animal.id} animal={animal} privileged={props.privileged} onDelete={props.onDelete} />)}
                        </CardColumns> :
                        <p className="text-center">Brak zwierząt</p>}
                </Col>
            </Row>
            <Row>
                <Col xs="6">
                    <u>{reportObj.occupiedPlaces} / {reportObj.maxAnimals} zajętych miejsc</u>
                </Col>
                <Col xs="6">
                    <b className={reportObj.shelterStatus === "FULL" ? "text-danger" : ""}>{reportObj.shelterStatus === "FULL" ?
                        "Schronisko pelne" : "Sa jeszcze wolne miejsca"}</b>
                </Col>
            </Row>
        </div>
    );  
};

export default Training;
