import React, { useState, useEffect }  from 'react'
import CvService from '../services/cvService';
import JobSeekerService from '../services/jobSeekerService';
import LanguageService from "../services/languageService";
import PhotoInfoService from "../services/photoInfoService";
import { Icon, Button, Card, Image, Rating } from "semantic-ui-react";
import { Link } from "react-router-dom";


export default function CvList() {
  const [cvs, setCvs] = useState([]);
  const [jobSeekers, setJobSeekers] = useState([]);
  const [photoInfos, setPhotoInfos] = useState([]);
  const [languages, setLanguages] = useState([]);

  useEffect(() => {
    let cvService = new CvService();
    let jobSeekerService = new JobSeekerService();
    let photoInfoService = new PhotoInfoService();
    let languageService = new LanguageService();

    cvService.getCvs().then((result) => setCvs(result.data.data));
    jobSeekerService.getJobSeekers().then((result) => setJobSeekers(result.data.data));
    photoInfoService.getAllPhoto().then((result) => setPhotoInfos(result.data.data));
    languageService.getLanguages().then((result) => setLanguages(result.data.data));
  });

  return (
    <div style={{ backgroundColor: "" }} className="ui four cards">
      <Card.Group>
        {cvs.map((cv) => (
          <Card fluid color="violet" >

            <Card.Content>
              <Image
                floated='right'
                size='small'
                src={cv.photoInfo.photoUrl}
              />
              <Card.Header style={{ marginLeft: "9em" }}>{cv.jobSeeker.firstname + " " + cv.jobSeeker.lastname }</Card.Header>
              <Card.Description style={{ marginLeft: "30em" }}>
                  {cv.educationInformationForCvs.schoolDepartmentName} </Card.Description>
              <Card.Content>{cv.coverLetter} <strong></strong>

              </Card.Content>
              <Rating icon='star' defaultRating={0} maxRating={5}>Rating</Rating>

            </Card.Content>
            <Card.Content extra>
              <div className='ui 2 buttons'>
                <Link to={`/cvDetails/${cv.cvId}`}>
                  <Button basic color='red'>
                    View
                  </Button></Link>
              </div>
            </Card.Content>
          </Card>
        ))}
      </Card.Group>
    </div>
    )
}
