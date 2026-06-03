import React from "react";
import {Card, CardImg, CardBody, CardTitle, CardSubtitle, CardText} from "reactstrap";
import DateTimeUtils from "../../utils/DateTimeUtils";

const Exercise = props => {
    const animalObj = props.animal;

    return (
        <Card>
            <CardImg top width="100%" src={`data:image/png;base64,${animalObj.base64Image}`} alt="Zdjęcie zwierzaka"></CardImg>
            <CardBody>
                <CardTitle><u>{animalObj.name}</u></CardTitle>
                <CardSubtitle><b>Płeć</b>: {animalObj.gender === "FEMALE" ? "żeńska" : "męska"}</CardSubtitle>
                <CardText>
                    <b>Wiek</b>: {animalObj.age}
                    <br />
                    <b>Data dołączenia</b>: {DateTimeUtils.defaultFormatDateTime(animalObj.dateOfArrival)}
                    <br />
                    {props.privileged && <button onClick={() => props.onDelete(animalObj)} type="button" className="btn btn-secondary btn-block">Usuń zwierzę</button>}
                </CardText>
            </CardBody>
        </Card>
    )
}

export default Exercise;
