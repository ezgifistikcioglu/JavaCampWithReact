import axios from "axios"

export default class JobSeekerService{
    getJobSeekers(){
        return axios.get("http://localhost:8080/api/job-seekers/getAll")
    }
    registerJobSeekers(values){
        return axios.post("http://localhost:8080/api/job-seekers/register",values)
    }
    getByJobSeekerId(id){
        return axios.get("http://localhost:8080/api/job-seekers/getById?id="+id)
    }
}