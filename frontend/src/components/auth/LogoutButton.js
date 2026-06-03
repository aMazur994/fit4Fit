import React from "react";
import {Button} from "reactstrap";
import {withRouter} from "react-router-dom";

const LogoutButton = props => {
    const logout = () => {
        sessionStorage.removeItem("jwt");
        props.history.push("/");
    };

    return (
        <Button color="info" onClick={logout}>Wyloguj się</Button>
    );
}

export default withRouter(LogoutButton);
