import AccountRegistrationForm from "../components/auth/AccountRegistrationForm";
import AccountLoginForm from "../components/auth/AccountLoginForm";
import {withRouter} from "react-router-dom";
import {Container, Row, Col} from "reactstrap";

const HomePage = props => {
  if(sessionStorage.getItem("jwt") !== null) {
    props.history.push("/profile");
  }

  return (
    <div>
      <Container>
        <Row>
          <Col>
            <h1 className="text-center">Asystent treningu</h1>
          </Col>
        </Row>
        <br />
        <Row>
          <Col xs="6">
            <AccountLoginForm />
          </Col>
          <Col xs="6">
            <AccountRegistrationForm />
          </Col>
        </Row>
        <br />
      </Container>
    </div>
  );
}

export default withRouter(HomePage);


