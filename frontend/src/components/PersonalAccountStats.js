import React, { Component } from 'react';
import Axios from 'axios';
import Config from "../Config";


class PersonalAccountStats extends Component {
    state = {
        weight: null,
        height: null,
        bmi: null,
        dateOfAdd: ""
    };


    componentDidMount() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }
        Axios.get(`${Config.AJAX_BASE_URL}/stats`, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(res => this.setState({
                weight: res.data.weight,
                height: res.data.height,
                bmi: res.data.bmi,
                dateOfAdd: res.data.dateOfAdd}))
            .catch(err => {
                if (err.response !== undefined && err.response.status === 401) {
                    // JWT expired (probably server restart)
                    sessionStorage.removeItem("jwt");
                    this.props.history.push("/");
                } else {
                    this.setState({
                        errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem",
                        account: null
                    });
                }
            });
    }

    render() {


        return (
            <div className="PersonalAccountStats" style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Informacje podstawowe</h3>
                <div>
                    <b>Masa ciała</b>: {this.state.weight ?? "Nie wprowadzonio"}
                    <br/>
                    <b>Wzrost</b>: {this.state.height ?? "Nie wprowadzonio"}
                    <br/>
                    <b>BMI</b>: {this.state.bmi ?? "Nie wprowadzonio"}
                    <br/>
                    <b>Data dodania</b>: {this.state.dateOfAdd}

                </div>
            </div>
        );
    }
}




export default PersonalAccountStats;