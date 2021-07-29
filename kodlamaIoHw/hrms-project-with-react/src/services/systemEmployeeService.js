import axios from "axios"

export default class SystemEmployeeService {
    getSystemEmployees(){
        return axios.get("http://localhost:8080/api/system_employees/getAll")
    }
    getById(id){
        return axios.get("http://localhost:8080/api/system_employees/getById/"+id)
    }
}
