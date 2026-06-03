import React from "react";
import {Route, withRouter} from "react-router-dom";
import {Container, Row, Col, Navbar, NavbarBrand, Nav, NavItem, CardColumns} from "reactstrap";
import LogoutButton from "../components/auth/LogoutButton";
import axios from "axios";
import DismissableAlert from "../utils/DismissableAlert";
import PersonalAccountProfile from "../components/PersonalAccountProfile";
import PersonalAccountStats from "../components/PersonalAccountStats";
import Config from "../Config";
import AddExerciseForm from "../components/other_crud/AddExerciseForm";
import ExerciseForm from "../components/other_crud/ExerciseForm";
import AddTraining from "../components/other_crud/AddTraining";
import StatsUpdate from "../components/StatsUpdate";
import Stopwatch from "../components/other_crud/Stopwatch";
import AddTrainingForm from "../components/other_crud/AddTrainingForm";
import TrainingList from "../components/other_crud/TrainingList";

class ProfilePage extends React.Component {
    state = {
        errorMessage: null,
        exercises: null,
        account: null,
        stats: null,
        trainings: null
    };


    componentDidMount() {

        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }

        axios.get(`${Config.AJAX_BASE_URL}/account`, {
            headers: {
                "x-auth": jwt
            }
        })
            .then(res => this.setState({account: res.data}))
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

    render() {
        const jwt = sessionStorage.getItem("jwt");
        if(jwt === null) {
            this.props.history.push("/");
        }



        return (
            <div>
                <Navbar color="light" light expand="md">
                    <NavbarBrand>Asystent treningu</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <LogoutButton />
                        </NavItem>
                    </Nav>
                </Navbar>

                <Container>
                    <Row>
                        <Col>
                            <h1 className="text-center">Panel użytkownika</h1>
                            {this.state.account !== null && <PersonalAccountProfile account={this.state.account} />}
                            <br />
                            <br />
                            {this.state.errorMessage !== null &&
                                <DismissableAlert key={Math.random()} color="danger" timeout={3} toggle={() => this.setState({errorMessage: null})}>
                                    {this.state.errorMessage}
                                </DismissableAlert>}
                        </Col>
                    </Row>
                    <br />
                    <br />
                    <br />
                    <Row>
                        <Col>
                            {<PersonalAccountStats  onExerciseForm={()=> this.forceUpdate()}/>}
                        </Col>
                    </Row>
                    <br />
                    <br />
                    <br />
                    <Row>
                        <Col>
                            <StatsUpdate  onStatsUpdateForm={()=> this.forceUpdate()}/>
                        </Col>
                    </Row>
                    <br />
                    <br />
                    <br />
                    <Row>
                        <Col>
                              <AddExerciseForm onAddExercise={() => this.forceUpdate()} />
                        </Col>
                    </Row>
                    <br />
                    <Row>
                        <Col>
                            <ExerciseForm onExerciseForm={()=> this.forceUpdate()}/>
                        </Col>
                    </Row>
                    <br/>

                    <Row>
                        <Col>
                            <AddTraining onAddTrainingForm = {() => this.forceUpdate()}/>
                        </Col>
                    </Row>
                    <br />
                    <Row>
                        <Col>
                            <Stopwatch />
                        </Col>
                    </Row>
                    <br />
                    <Row>
                        <Col>
                            <TrainingList  onTrainingListForm={()=> this.forceUpdate()}/>
                        </Col>
                    </Row>
                    <br />
                    <br />

                </Container>
            </div>
        );
    }
}

export default withRouter(ProfilePage);
