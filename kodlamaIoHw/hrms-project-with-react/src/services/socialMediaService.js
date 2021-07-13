import axios from "axios"

export default class SocialMediaService {
    getSocialMedias(){
        return axios.get("http://localhost:8080/api/social-medias/getAll")
    }
    addSocialMedias(values){
        return axios.post("http://localhost:8080/api/social-medias/add",values)
    }
    updateSocialMedias(values){
        return axios.post("http://localhost:8080/api/social-medias/update",values)
    }
    findAllById(id){
        return axios.get("http://localhost:8080/api/social-medias/findAllById?id="+id)
    }
    getByCvId(id){
        return axios.get("http://localhost:8080/api/social-medias/getByCvId/"+id)
    }
}
