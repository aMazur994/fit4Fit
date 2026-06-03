import React from "react";
import axios from "axios";
import Config from "../Config";
import DismissableAlert from "../utils/DismissableAlert";
import {withRouter} from "react-router-dom";

class StatsUpdate extends React.Component  {
    state = {
        weight: null,
        height: null,
        bmi: null,
        dateOfAdd: null,
        errorMessage: null
    }
    updateStats = e => {
        e.preventDefault();

        const jwt = sessionStorage.getItem("jwt");
        e.target.reset();

        axios.post(`${Config.AJAX_BASE_URL}/statsAdd`, {
            weight: this.state.weight,
            height: this.state.height
        }, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.setState({
                successMessage: `Pomyślnie dodano statystyki `
            }))
            .catch(err => this.setState({
                errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem!"
            }));
    };
    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});
    render()
    {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Aktualizuj dane</h3>
                <form onSubmit={this.updateStats}>
                    <div className="form-group">
                        <label htmlFor={`weight_${randomValueForId}`}>Waga</label>
                        <input type="number" name="weight" className="form-control" id={`weight_${randomValueForId}`}
                               onChange={this.genericOnChangeEventHandler}
                               required={true} placeholder="Wpisz wage"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor={`height_${randomValueForId}`}>Wzrost</label>
                        <input type="number" name="height" className="form-control" id={`height_${randomValueForId}`}
                               onChange={this.genericOnChangeEventHandler}
                               required={true} placeholder="Wpisz wzrost"/>
                    </div>

                    <button type="submit" className="btn btn-primary">Zapisz</button>

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

export default withRouter(StatsUpdate);

