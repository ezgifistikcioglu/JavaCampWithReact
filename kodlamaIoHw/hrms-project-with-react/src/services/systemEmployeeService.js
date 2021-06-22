import axios from "axios"

export default class systemEmployeeService {
    getSystemEmployees(){
        return axios.get("http://localhost:8080/api/system_employees/getAll")
    }
}
