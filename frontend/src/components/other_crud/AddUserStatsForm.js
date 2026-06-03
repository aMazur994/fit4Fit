import React from "react";
import {withRouter} from "react-router-dom";
import DismissableAlert from "../../utils/DismissableAlert";
import axios from "axios";
import Config from "../../Config";

class AddUserStatsForm extends React.Component {
    state = {
        weight: "",
        height: "",
        errorMessage: null
    }

    onAddStats = e => {
        e.preventDefault();

        const jwt = sessionStorage.getItem("jwt");
        e.target.reset();

        axios.post(`${Config.AJAX_BASE_URL}/stats`, {
            weight: this.state.weight,
            height: this.state.height,
        }, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.props.onAddStats())
            .catch(err => {
                if(err.response !== undefined && err.response.status === 401) {
                    // JWT expired (probably server restart)
                    sessionStorage.removeItem("jwt");
                    this.props.history.push("/");
                } else {
                    this.setState({errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem"});
                }
            });
    };

    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});

    render() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Dodaj nowe ćwiczenie</h3>
                <form onSubmit={this.onAddStats}>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Waga</label>
                        <input type="text" name="weight" className="form-control" id={`weight_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                               required={true} placeholder="Podaj masę ciała" />
                    </div>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Wzrost</label>
                        <input type="text" name="height" className="form-control" id={`height_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                               required={true} placeholder="Podaj wzrost" />
                    </div>
                    <button type="submit" className="btn btn-primary">Dodaj</button>

                    {this.state.errorMessage !== null &&
                        <div>
                            <br />
                            <br />
                            <DismissableAlert key={Math.random()} color="danger" timeout={3} toggle={() => this.setState({errorMessage: null})}>
                                {this.state.errorMessage}
                            </DismissableAlert>
                        </div>
                    }
                </form>
            </div>
        );
    }
}

export default withRouter(AddUserStatsForm);