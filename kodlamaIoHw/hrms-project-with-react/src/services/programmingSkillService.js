import axios from "axios"

export default class ProgrammingSkillService {
    getProgrammingSkills(){
        return axios.get("http://localhost:8080/api/programming-skills/getAll")
    }
    addProgrammingSkills(values){
        return axios.post("http://localhost:8080/api/programming-skills/add",values)
    }
    updateProgrammingSkills(values){
        return axios.put("http://localhost:8080/api/programming-skills/update",values)
    }
    findAllByCvId(id){
        return axios.get("http://localhost:8080/api/programming-skills/findAllByCvId/"+id)
    }
    getByCvId(id){
        return axios.get("http://localhost:8080/api/programming-skills/getByCvId/"+id)
    }
}
