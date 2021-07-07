import React from "react";
import "../App.css";
import { Grid, Icon, Card, Image } from "semantic-ui-react";
import image from "../images/me.jpg";
import getLink from "../../utilities/cvs/getLink";

import dataFile from "../resources/Data.json";
const [jobSeekers, setJobSeekers] = useState([]);

useEffect(() => {
    let  jobSeekerService = new JobSeekerService();
    jobSeekerService.getJobSeekers().then(result=>setJobSeekers(result.data.data))
  }, [])



export default () => (
  <Grid.Row>
    <h2>
      <Card>
        <Image src={image} />
        <Card.Content>
          <Card.Header>
            <h2><Icon name="user circle" /> {jobSeekers.firstname}</h2>
          </Card.Header>
        </Card.Content>
        <Card.Content className="black">
        {jobSeekers.map(jobSeeker => (
          <p>
            <b>{jobSeeker.lastname}</b><br/>
            {jobSeeker.email}
          </p>
        ))}
        <br/>
        <Grid>
          {jobSeekers.map(link => (
            <span style={{marginBottom: "0.5em"}}>
              {getLink(link.name, link.link)}
            </span>
          ))}
        </Grid>
        </Card.Content>
      </Card>
    </h2>
  </Grid.Row>
);