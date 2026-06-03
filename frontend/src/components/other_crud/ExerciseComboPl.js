import React, { useState } from "react";
import ExercisesListFunction from "./ExercisesListFunction";
import {getReps, getSec, arra, getCombobox, arrayRange} from "./intFunctions/IntSecFunction";
import axios from "axios";
import Config from "../../Config";


const reps = arrayRange(1,25,1);
const series = arrayRange(1,8,1);
const sec = arrayRange(0,400,15);


const ExerciseComboPl = () => {
    //state = {
    //    id: null,
    //    name: "",
    //    bodyPart: "",
    //    errorMessage: null
    //}
        const [rows, setRows] = useState([{}]);
    const collumnsArray = ["Nazwa ćwiczenia", "Ilość serii", "Powtórzenia min", "Powtórzenia max", "Przerwa [s]"]; // pass collumns here dynamically
    const handleAddRow = () => {
        const item = {};
        setRows([...rows, item]);
    };

    const postResults = e => {
        e.preventDefault();

        const jwt = sessionStorage.getItem("jwt");
        e.target.reset();

        axios.post(`${Config.AJAX_BASE_URL}/training`, {
            name: this.state.name,
            bodyPart: this.state.bodyPart,
        }, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(() => this.props.onAddExercise())
            .catch(err => this.setState({
                errorMessage: err.response !== undefined ? err.response.data : "Brak połączenia z serwerem!"
            }));
    };
    const handleRemoveSpecificRow = (idx) => {
        const tempRows = [...rows]; // to avoid  direct state mutation
        tempRows.splice(idx, 1);
        setRows(tempRows);
    };
    const genericOnChangeEventHandler = e => this.setState({[e.rows.name]: e.target.value});


    const updateState = (e) => {
        let prope = e.target.attributes.collumn.value; // the custom collumn attribute
        let index = e.target.attributes.index.value; // index of state array -rows
        let fieldValue = e.target.value; // value
        let maxValue = 5;

        const tempRows = [...rows]; // avoid direct state mutation
        const tempObj = rows[index]; // copy state object at index to a temporary object
        tempObj[prope] = fieldValue; // modify temporary object

        // return object to rows` clone
        tempRows[index] = tempObj;
        setRows(tempRows); // update state
    };


    const randomValueForId = Math.round(Math.random() * 100);


    return (
        <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
            <h3>Dodaj Trening</h3>
            <div className="container">
                <div className="row clearfix">
                    <div className="col-md-12 column">
                        <table className="table table-bordered table-hover" id="tab_logic">

                            <thead>
                            <tr>
                                <th className="col-form-label pt-0"> # </th>
                                {collumnsArray.map((collumn, index) => (
                                    <th className="col-form-label pt-0" key={index}>
                                        {collumn}
                                    </th>
                                ))}
                                <th />
                            </tr>
                            </thead>
                            <tbody>
                            {rows.map((item, idx) => (
                                <tr key={idx}>
                                    <td>{idx + 1}</td>
                                    <th>
                                        <ExercisesListFunction/>
                                    </th>
                                    <th>
                                        {getCombobox(series)}
                                    </th>
                                    <th>
                                        {getCombobox(reps)}
                                    </th>
                                    <th>
                                        {getCombobox(reps)}
                                    </th>
                                    <th>
                                        {getCombobox(sec)}
                                    </th>
                                    <td>
                                        <button
                                            className="btn btn-outline-danger btn-sm"
                                            onClick={() => handleRemoveSpecificRow(idx)}
                                        >
                                            Usuń
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <button onClick={handleAddRow} className="btn btn-primary">
                           Dodaj ćwiczenie
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ExerciseComboPl;