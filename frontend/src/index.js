import React from "react";
import ReactDOM from "react-dom";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";
import "bootstrap/dist/css/bootstrap.min.css";

import {HashRouter as Router, Route} from "react-router-dom";

ReactDOM.render(
    <Router>
        <div>
            <Route exact path="/" component={HomePage} />
            <Route exact path="/profile" component={ProfilePage} />
        </div>
    </Router>
, document.getElementById('root'));
