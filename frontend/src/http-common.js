import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8050/api",
  headers: {
    "Content-type": "application/json"
  }
});
