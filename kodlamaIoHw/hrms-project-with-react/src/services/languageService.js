import axios from "axios"

export default class LanguageService{
    getLanguages(){
        return axios.get("http://localhost:8080/api/languages/getAll")
    }
    getLanguagesWithId(id){
        return axios.get("http://localhost:8080/api/languages/findAllByLanguageId?id=",id)
    }
    addLanguages(values){
        return axios.post("http://localhost:8080/api/languages/add",values)
    }
}