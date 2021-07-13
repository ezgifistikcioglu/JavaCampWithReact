import React, { useState, useEffect }  from 'react'
import CvService from '../services/cvService';
import { Button, Card, Image, Rating } from "semantic-ui-react";
import { Link } from "react-router-dom";


export default function CvList() {
  let cvService = new CvService();

  const [cvs, setCvs] = useState([]);
  
  useEffect(() => {
 
    cvService.getCvs().then((result) =>{
      setCvs(result.data.data);
    });
    
  }, []);

  return (
    <div style={{ backgroundColor: "" }} className="ui four cards">
      <Card.Group>
        {cvs?.map((cv) => (
          <Card fluid color="orange"  key= {cv.cvId} >
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
