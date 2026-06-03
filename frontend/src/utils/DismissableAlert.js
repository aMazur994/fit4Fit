import React from "react";
import {Alert} from "reactstrap";

const DismissableAlert = props => {
    setTimeout(props.toggle, props.timeout * 1000);

    return (
        <Alert color={props.color}>
            {props.children}
        </Alert>
    );
}

export default DismissableAlert;
