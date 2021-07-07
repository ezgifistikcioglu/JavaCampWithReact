import axios from "axios"

export default class CvService{
    getCvs(){
        return axios.get("http://localhost:8080/api/cvs/getAll")
    }
    addCvs(values){
        return axios.post("http://localhost:8080/api/cvs/add",values)
    }
    updateCvs(values){
        return axios.post("http://localhost:8080/api/cvs/update",values)
    }
    getByCvId(id){
        return axios.get("http://localhost:8080/api/cvs/getByCvId?id="+id)
    }
    findByJobSeekerUserId(id){
        return axios.get("http://localhost:8080/api/cvs/findByJobSeekerUserId?id="+id)
    }
}