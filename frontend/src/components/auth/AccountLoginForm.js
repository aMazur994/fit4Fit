import React from "react";
import DismissableAlert from "../../utils/DismissableAlert";
import axios from "axios";
import {withRouter} from "react-router-dom";
import Config from "../../Config";

class AccountLoginForm extends React.Component {
    state = {
        name: "",
        password: "",

        errorMessage: null
    };

    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});

    onLogin = e => {
        e.preventDefault();

        // check for one-word name
        if(this.state.name.match(/\b\w+\b/g) > 1) {
            this.setState({errorMessage: "Login musi być jednowyrazowy!"});
            return;
        }

        e.target.reset();

        axios.post(`${Config.AJAX_BASE_URL}/login`, {
            name: this.state.name,
            password: this.state.password
        })
            .then(res => {
                sessionStorage.setItem("jwt", res.headers["x-auth"]);
                this.props.history.push("/profile");
            })
            .catch(err => this.setState({
                errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem!"
            }));
    };

    render() {
        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Zaloguj się</h3>
                <form onSubmit={this.onLogin}>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Nazwa/login</label>
                        <input type="text" name="name" className="form-control" id={`name_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz swój login..." />
                            </div>
                    <div className="form-group">
                        <label htmlFor={`password_${randomValueForId}`}>Hasło</label>
                        <input type="password" name="password" className="form-control" id={`password_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz swoje hasło..." />
                    </div>
                    <button type="submit" className="btn btn-primary">Logowanie</button>

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

export default withRouter(AccountLoginForm);
