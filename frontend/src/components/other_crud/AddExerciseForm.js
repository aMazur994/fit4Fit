import React from "react";
import {withRouter} from "react-router-dom";
import DismissableAlert from "../../utils/DismissableAlert";
import axios from "axios";
import Config from "../../Config";

class AddExerciseForm extends React.Component {
    state = {
        id: null,
        name: "",
        metValue: "",
        bodyPart: "",
        errorMessage: null
    }

    onAddExercise = e => {
        e.preventDefault();

        const jwt = sessionStorage.getItem("jwt");
        e.target.reset();
        
        axios.post(`${Config.AJAX_BASE_URL}/exercise`, {
            name: this.state.name,
            bodyPart: this.state.bodyPart,
        }, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.setState({
                successMessage: `Pomyślnie dodano ćwiczenie ${this.state.name}`}))
            .catch(err => this.setState({
                errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem!"
            }));
    };

    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});
    genericOnChangeCheckboxesEventHandler = e => {
        if(e.target.checked) {
            this.setState({[e.target.name]: e.target.value});
        }
    }

    render() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Dodaj nowe ćwiczenie</h3>
                <form onSubmit={this.onAddExercise}>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Nazwa</label>
                        <input type="text" name="name" className="form-control" id={`name_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                               required={true} placeholder="Wpisz nazwę ćwiczenia..." />
                            </div>
                    <fieldset className="form-group" >
                        <legend className="col-form-label pt-0">Część ciała</legend>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_legs_${randomValueForId}`} value="LEGS"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_legs_${randomValueForId}`}>nogi</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_legs_${randomValueForId}`} value="SHOULDERS"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_legs_${randomValueForId}`}>barki</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_back_${randomValueForId}`} value="BACK"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_back_${randomValueForId}`}>plecy</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_arms_${randomValueForId}`} value="ARMS"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_arms_${randomValueForId}`}>ramiona</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_abdominal_${randomValueForId}`} value="ABDOMINAL"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_abdominal_${randomValueForId}`}>brzuch</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="radio" name="bodyPart"
                                   onChange={this.genericOnChangeCheckboxesEventHandler}
                                   id={`bodyPart_chest_${randomValueForId}`} value="CHEST"/>
                            <label className="form-check-label"
                                   htmlFor={`bodyPart_chest_${randomValueForId}`}>klatka piersiowa</label>
                        </div>
                    </fieldset>
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

export default withRouter(AddExerciseForm);