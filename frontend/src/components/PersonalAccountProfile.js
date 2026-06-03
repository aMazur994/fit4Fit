import React from "react";
import DateTimeUtils from "../utils/DateTimeUtils";
import {Card, CardImg, CardBody, CardTitle, CardText} from "reactstrap";

const PersonalAccountProfile = props => {
    const account = props.account;

    return (
        <Card className="text-center" style={{border: "4px black solid", paddingBottom: "10px"}}>
            <CardImg className="mx-auto" top style={{width: "30%", height: "30e:%"}} src={`data:image/png;base64,${account.base64Image}`} alt="Zdjęcie profilowe pracownika"></CardImg>
            <CardBody>
                <CardTitle><u>{account.name}</u></CardTitle>
                <CardText>
                    <b>Email</b>: {account.email}
                    <br />
                    <b>Data dołączenia</b>: {DateTimeUtils.defaultFormatDateTime(account.dateOfAdd)}
                </CardText>
                <br/>
            </CardBody>
        </Card>
    )
}

export default PersonalAccountProfile;
