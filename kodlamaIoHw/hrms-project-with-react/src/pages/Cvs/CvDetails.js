import React, { useState, useEffect } from "react";
import "../../App.css";
import { Grid, Icon, Card, Image, Table } from "semantic-ui-react";
import image from "../../images/me.jpg";
import { useParams } from "react-router-dom";
import CvService from '../../services/cvService';


export default function CvDetails() {
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
                            <Card.Header>
                                <h2><Icon name="user circle" /> {cvs.jobSeeker?.firstname + "-" + cvs.jobSeeker?.lastname}</h2>
                            </Card.Header>
                            {cvs.map(cv => (
                                <Table.Row key={cv.jobSeeker.userId}>
                                    <Table.Cell>{"cv.jobSeeker.email"}</Table.Cell>
                                    <Table.Cell>{"cv.jobSeeker.firstname"}</Table.Cell>
                                    <Table.Cell>{"cv.jobSeeker.lastname"}</Table.Cell>
                                    <Table.Cell>{"cv.jobSeeker.tcNo"}</Table.Cell>
                                    <Table.Cell>{"cv.jobSeeker.birthYear"}</Table.Cell>
                                </Table.Row>
                            ))}
                    </Card.Content>
                    <Card.Content className="black">

                        <Grid>
                            {"Github"}<br />
                            {"Linkedin"}
                        </Grid>
                    </Card.Content>
                </Card>
            </h2>
        </Grid.Row>
    );
}