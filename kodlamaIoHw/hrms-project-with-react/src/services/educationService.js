import axios from "axios"

export default class EducationService{
    getEducations(){
        return axios.get("http://localhost:8080/api/educations/getAll")
    }
    addEducations(values){
        return axios.post("http://localhost:8080/api/educations/add",values)
    }
    updateEducations(values){
        return axios.put("http://localhost:8080/api/educations/update",values)
    }
    findByEducationIdOrderBySchoolGraduationDate(id){
        return axios.get("http://localhost:8080/api/educations/findByEducationIdOrderBySchoolGraduationDate?d=ASC&id="+id)
    }
    getByEducationId(id){
        return axios.get("http://localhost:8080/api/educations/getByEducationId/"+id)
    }
    getByCvId(id){
        return axios.get("http://localhost:8080/api/educations/getByCvId/"+id)
    }
}