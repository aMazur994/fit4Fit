import Axios from "axios";
import Config from "../../Config";
import {Component} from "react";

export default class TrainingList extends Component {
    state = {
        trainings: []
    };


    componentDidMount() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }
        Axios.get(`${Config.AJAX_BASE_URL}/training`,{
            headers: {
                "x-auth": jwt
            }
        })
            .then(res => this.setState({trainings: res.data}))
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
     formatDate = (dateString) => {
        // Create a new Date object from the dateString
        const date = new Date(dateString);

        // Extract year, month, day, hours, and minutes from the Date object
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');

        // Construct the formatted date string in the desired format
        const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}`;

        return formattedDate;
    };




    render() {


        return this.state.trainings !== 0 ? (
            <div style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
                <h3>Lista treningów</h3>
                <table  id = "exercise">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Data treningu</td>
                        <td>Czas treningu</td>
                        <td>Spalone kalorie</td>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.trainings.map((training, index) => {
                        const dateOfTraining = this.formatDate(new Date(training.dateOfTraining).toLocaleString());
                        const cal = training.calories.toFixed(2);
                        return (

                            <tr key={training}>
                                <td>{index}</td>
                                <td>{dateOfTraining}</td>
                                <td>{training.trainingDurationtos}</td>
                                <td>{cal}</td>
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