import axios from 'axios';
import { useEffect, useState } from 'react';
import Config from "../../Config";
import ExerciseForm from "./ExerciseForm";

function ExerciseListForm() {
    const [dataTable, setDataTable] = useState([]);

    useEffect(() => {
        axios.get(`${Config.AJAX_BASE_URL}/exercises`)
            .then(res => setDataTable(res.data))
            .catch(err => console.log(err))
    }, []);

    const column = [
        { heading: 'Nazwa', value: 'name' },
        { heading: 'Czêœæ cia³a', value: 'body_part' }
    ]

    return (
        <div className="App">
            <h1>Æwiczenia</h1>
            <ExerciseForm data={dataTable} column={column} />
        </div>
    );
}

export default ExerciseListForm;