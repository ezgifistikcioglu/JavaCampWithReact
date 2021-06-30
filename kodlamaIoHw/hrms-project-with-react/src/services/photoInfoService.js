import axios from "axios"

export default class PhotoInfoService{
    getAllPhoto(){
        return axios.get("http://localhost:8080/api/images/getAll")
    }
    getAllPhotoWithId(id){
        return axios.get("http://localhost:8080/api/images/getById?id="+id)
    }

    upload(cvId,id){
        return axios.post(`http://localhost:8080/api/images/upload?cvId=${cvId}&id=${id}`)
    }
   
    getAllOpenAndApprovedAdvertisementList(){
        return axios.get("http://localhost:8080/api/advertisements/getAllOpenAndApprovedAdvertisementList")
    }
    getAllWaitApproveAdvertisementList(){
        return axios.get("getAllWaitApproveAdvertisementList")
    }
}