import axios from "axios"

export default class WorkTypeService{
    getworkTypes(){
        return axios.get("http://localhost:8080/api/work-types/getAllWorkType")
    }
}