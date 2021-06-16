import axios from "axios"

export default class WorkTimeService{
    getworkTimes(){
        return axios.get("http://localhost:8080/api/work-times/getAllWorkTime")
    }
}