import React, { useState, useEffect } from "react";
import "../App.css";
import { Grid, Icon, Card, Image } from "semantic-ui-react";
import image from "../images/me.jpg";
import { useParams } from "react-router-dom";
import JobSeekerService from '../services/jobSeekerService';
import CvService from '../services/cvService';


export default function Settings() {
  let { id } = useParams();
  const [jobSeekers, setJobSeekers] = useState([]);
  const [cvs, setCvs] = useState([]);

  useEffect(() => {
    let cvService = new CvService();
    cvService.findByJobSeekerUserId(id).then((result) => setCvs(result.data.data));
    // let jobSeekerService = new JobSeekerService();
    // jobSeekerService.getJobSeekers().then((result) => setJobSeekers(result.data.data));
  }, [id]);
  return (
    <Grid.Row>
      <h2>
        <Card>
          <Image src={image} />
          <Card.Content>
          <Grid>
          <Card.Header>
              <h2><Icon name="user circle" /> {cvs.jobSeeker?.firstname + "-" + cvs.jobSeeker?.lastname}</h2>
            </Card.Header>
            {cvs.map(cv => (
              <p>
                {cv.jobSeeker.email}
                {cv.firstname}
                {cv.lastname}
                {cv.tcNo}
                {cv.birthYear}
              </p>
            ))}
             </Grid>
            <br />

          </Card.Content>
          <Card.Content className="black">

            <Grid>
              {"Github"}<br/>
              {"Linkedin"}
            </Grid>
          </Card.Content>
        </Card>
      </h2>
    </Grid.Row>
  );
}