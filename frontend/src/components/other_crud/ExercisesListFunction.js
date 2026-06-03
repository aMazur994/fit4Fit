import axios from "axios";
import {useEffect, useState} from "react";
import Config from "../../Config";

function ExercisesListFunction() {
    const [exercises, setExercise] = useState([]);

    useEffect(() => {
        async function fetchData() {
            // Fetch data
            axios.get(`${Config.AJAX_BASE_URL}/exercises`)
                .then((response) => {
                    setExercise(response.data);
                }, []);
        }

        // Trigger the fetch
        fetchData();
    }, []);

    return (
        <div className="ExercisesListFunction">
            <select onChange={(e) => {
                const c = exercises?.find((x) => x.id === e.target.value);
                console.log(c);
            }}>
                {exercises
                    // eslint-disable-next-line array-callback-return
                    ? exercises.map((exercise) => {
                        return (<option key={exercise.id} value={exercise.id}>
                            {exercise.name}
                        </option>);
                    })
                    : null}
            </select>
        </div>
    );
}

export default ExercisesListFunction;