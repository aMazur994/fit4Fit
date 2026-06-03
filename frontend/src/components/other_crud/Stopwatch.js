import React, {useRef, useState} from 'react';
import axios from "axios";
import Config from "../../Config";

function Stopwatch() {

    const [timer, setTimer] = useState(0);
    const [isRunning, setIsRunning] = useState(false);

    let timeInterval = useRef(null);
    const handleStart = () => {
        if (isRunning) return;
        setIsRunning(true);
        timeInterval.current = setInterval(() => {
            setTimer((prev) => prev + 1);
        }, 10);
    };

    const handleReset = () => {

        const millisecondsValue = parseInt(timer, 10);
        const minutesValue = millisecondsValue / (100 * 60);

        const jwt = sessionStorage.getItem("jwt");
        const minutes = {
            minutes: minutesValue
        };
        axios.post(`${Config.AJAX_BASE_URL}/training`,{
            minutes: minutesValue
        },{
            headers: {
                "x-auth": jwt
            }
        })
            .then(response => {
                console.log('Time posted successfully:', response.data);
                // You can handle success response here
            })
            .catch(error => {
                console.error('Error posting time:', error);
                // You can handle error response here
            });


        setIsRunning(false);
        clearInterval(timeInterval.current);
        setTimer(0);
    };

    const formatTime = (timer) => {
        const hours = Math.floor(timer / 360000);

        // Minutes calculation
        const minutes = Math.floor((timer % 360000) / 6000);

        // Seconds calculation
        const seconds = Math.floor((timer % 6000) / 100);

        // Milliseconds calculation
        const milliseconds = timer % 100;

        return {hours, minutes, seconds, milliseconds};
    };

    const {hours, minutes, seconds, milliseconds} = formatTime(timer);

    return (
        <div className="stopwatch" style={{padding: "20px", border: "4px black solid", borderRadius: "8px"}}>
            <h3>Trening</h3>
            <table className="table table-bordered table-hover" id="tab_logic">
                <thead>
                <tr>
                    <td>godziny</td>
                    <td>minuty</td>
                    <td>sekundy</td>
                    <td>milisekundy</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{hours}</td>
                    <td>{minutes}</td>
                    <td>{seconds}</td>
                    <td>{milliseconds}</td>
                </tr>
                </tbody>
            </table>

            <div className="button-container">
                <button className="btn btn-primary" onClick={handleStart}>Rozpocznij trening</button>
                <button className="btn btn-outline-danger btn-sm" onClick={handleReset}>Zakończ trening</button>
            </div>
        </div>
    );
}

export default Stopwatch
