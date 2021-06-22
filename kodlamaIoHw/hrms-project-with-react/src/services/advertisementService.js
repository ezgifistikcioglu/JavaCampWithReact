import axios from "axios"

export default class AdvertisementService{
    getAdvertisements(){
        return axios.get("http://localhost:8080/api/advertisements/getAllAdvertisementList")
    }
    addAdvertisements(values){
        return axios.post("http://localhost:8080/api/advertisements/addAdvertisement",values)
    }
    findById(id){
        return axios.get("http://localhost:8080/api/advertisements/findById?id="+id)
    }
    getAllOpenAndApprovedAdvertisementList(){
        return axios.get("http://localhost:8080/api/advertisements/getAllOpenAndApprovedAdvertisementList")
    }
    getAllWaitApproveAdvertisementList(){
        return axios.get("getAllWaitApproveAdvertisementList")
    }
}