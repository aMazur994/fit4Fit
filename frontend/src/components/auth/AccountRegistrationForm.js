import React from "react";
import DismissableAlert from "../../utils/DismissableAlert";
import axios from "axios";
import Config from "../../Config";

class AccountRegistrationForm extends React.Component {
    state = {
        name: "",
        password: "",
        email: "",
        base64Image: "",
        errorMessage: null,
        successMessage: null
    };

    genericOnChangeEventHandler = e => this.setState({[e.target.name]: e.target.value});

    onRegistration = e => {
        e.preventDefault();

        // check for one-word name
        if(this.state.name.match(/\b\w+\b/g) > 1) {
            this.setState({errorMessage: "Login musi być jednowyrazowy!"});
            return;
        }

        e.target.reset();

        axios.post(`${Config.AJAX_BASE_URL}/accounts`, {
            name: this.state.name,
            password: this.state.password,
            email: this.state.email,
            base64Image: this.state.base64Image
        })
            .then(() => this.setState({
                successMessage: `Pomyślnie zarejestrowano użytkownika ${this.state.name}`
            }))
            .catch(err => this.setState({
                errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem!"
            }));
    };

    render() {
        const randomValueForId = Math.round(Math.random() * 100);

        return (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Zarejestruj się</h3>
                <form onSubmit={this.onRegistration}>
                    <div className="form-group">
                        <label htmlFor={`name_${randomValueForId}`}>Nazwa/login</label>
                        <input type="text" name="name" className="form-control" id={`name_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz swój login..." />
                    </div>
                    <div className="form-group">
                        <label htmlFor={`password_${randomValueForId}`}>Hasło</label>
                        <input type="password" name="password" minLength={8} className="form-control" id={`password_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz swoje hasło..." />
                    </div>
                    <div className="form-group">
                        <label htmlFor={`email_${randomValueForId}`}>Email</label>
                        <input type="email" name="email" className="form-control" id={`email_${randomValueForId}`} onChange={this.genericOnChangeEventHandler}
                            required={true} placeholder="Wpisz swój email..." />
                    </div>
                    <div className="form-group">
                        <label htmlFor={`image_${randomValueForId}`}>Zdjęcie profilowe</label>
                        <input required={false} type="file" className="form-control-file" lang={"PL"} id={`image_${randomValueForId}`}
                            accept="image/png" onChange={e => {
                                const file = e.target.files[0];

                                if(file != null && file.type !== "image/png") {
                                    this.setState({errorMessage: `Zły format zdjęcia profilowego (${file.type} zamiast png)`});
                                    e.target.value = "";
                                    return;
                                }

                                const fileReader = new FileReader();
                                fileReader.readAsBinaryString(file);
                                fileReader.onload = () => this.setState({base64Image: btoa(fileReader.result)});
                            }}/>
                    </div>
                    <button type="submit" className="btn btn-primary">Rejestracja</button>

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

export default AccountRegistrationForm;
