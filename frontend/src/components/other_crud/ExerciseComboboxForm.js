import React, {Component} from "react";
import Axios from "axios";
import Config from "../../Config";

export default class ExerciseForm extends Component {
    state = {
        exercises: []
    };


    componentDidMount() {
        Axios.get(`${Config.AJAX_BASE_URL}/exercises`)
            .then(res => this.setState({exercises: res.data}))
            .catch(err => {
                if(err.response !== undefined && err.response.status === 401) {
                    // JWT expired (probably server restart)
                    sessionStorage.removeItem("jwt");
                    this.props.history.push("/");
                } else {
                    this.setState({
                        errorMessage: err.response !== undefined ? err.response.data : "Brak po³¹czenia z serwerem",
                        account: null
                    });
                }
            });
    }
    render() {
        let myDictionary = {};
        myDictionary["LEGS"]="nogi";
        myDictionary["BACK"]="plecy";
        myDictionary["ARMS"]="ramiona";
        myDictionary["ABDOMINAL"]="brzuch";
        myDictionary["CHEST"]="klatka piersiowa";

        return this.state.exercises !== 0 ? (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <select className="combo">
                    {this.state.exercises.map((exercise,index) => {
                        return (
                            <option>
                                <td>{exercise.name}</td>
                            </option>
                        );
                    })}
                </select>
            </div>
        ) : (
            <h1>Nie znaleziono æwiczeñ</h1>
        );
    }
}