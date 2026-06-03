import React, { useState } from "react";
import ExercisesListFunction from "./ExercisesListFunction";

const AddTrainingForm = () => {
    const [rows, setRows] = useState([{}]);
    const collumnsArray = ["Nazwa æwiczenia", "Iloœæ serii", "Powtórzenia min", "Powtórzenia max", "Przerwa"]; // pass collumns here dynamically

    const handleAddRow = () => {
        const item = {};
        setRows([...rows, item]);
    };

    const postResults = () => {
        console.log(rows); // there you go, do as you please
    };
    const handleRemoveSpecificRow = (idx) => {
        const tempRows = [...rows]; // to avoid  direct state mutation
        tempRows.splice(idx, 1);
        setRows(tempRows);
    };

    const updateState = (e) => {
        let prope = e.target.attributes.collumn.value; // the custom collumn attribute
        let index = e.target.attributes.index.value; // index of state array -rows
        let fieldValue = e.target.value; // value

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
                                    {collumnsArray.map((collumn, index) => (
                                        <td key={index}>
                                            <input
                                                type="text"
                                                collumn={collumn}
                                                value={rows[idx][collumn]}
                                                index={idx}
                                                className="form-control"
                                                onChange={(e) => updateState(e)}

                                            />
                                        </td>
                                    ))}

                                    <td>
                                        <button
                                            className="btn btn-outline-danger btn-sm"
                                            onClick={() => handleRemoveSpecificRow(idx)}
                                        >
                                            Usuñ
                                        </button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <button onClick={handleAddRow} className="btn btn-primary">
                            Add Row
                        </button>
                        <button
                            onClick={postResults}
                            className="btn btn-success float-right"
                        >
                            Save Results
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddTrainingForm;