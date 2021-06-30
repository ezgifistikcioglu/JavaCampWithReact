import axios from "axios"

export default class WorkExperienceService {
    getWorkExperiences(){
        return axios.get("http://localhost:8080/api/work-experiences/getAll")
    }
    addWorkExperiences(values){
        return axios.post("http://localhost:8080/api/work-experiences/add",values)
    }
    updateWorkExperiences(values){
        return axios.post("http://localhost:8080/api/work-experiences/update",values)
    }
    findByExperienceId(id){
        return axios.get("http://localhost:8080/api/work-experiences/findByExperienceId?id="+id)
    }
}
