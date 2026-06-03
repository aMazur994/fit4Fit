import React, { Component } from 'react';
import Axios from 'axios';
import Config from "../../Config";
import './table.css'
import axios from "axios";


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
                        errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem",
                        account: null
                    });
                }
            });
    }
    deleteRow(id) {
        const jwt = sessionStorage.getItem("jwt");

        axios.delete(`${Config.AJAX_BASE_URL}/exercise/${id}`, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.forceUpdate())
            .then(res => this.setState({exercises: res.data}))
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



    render() {
        let myDictionary = {};
            myDictionary["LEGS"]="nogi";
            myDictionary["BACK"]="plecy";
            myDictionary["ARMS"]="ramiona";
            myDictionary["ABDOMINAL"]="brzuch";
            myDictionary["CHEST"]="klatka piersiowa";
            myDictionary["SHOULDERS"]="barki";

        return this.state.exercises !== 0 ? (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Lista ćwiczeń</h3>
            <table  id = "exercise">
                <thead>
                <tr>
                    <td>Identyfikator</td>
                    <td>Nazwa</td>
                    <td>Część ciała</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                {this.state.exercises.map((exercise,index) => {
                    return (
                        <tr key={exercise}>
                            <td>{index+1}</td>
                            <td>{exercise.name}</td>
                            <td>{myDictionary[exercise.bodyPart]}</td>
                            <td><button className="btn btn-danger" onClick={(e) => this.deleteRow(exercise.id)}>Usuń</button></td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
            </div>
        ) : (
            <h1>No data available!</h1>
        );
    }
}