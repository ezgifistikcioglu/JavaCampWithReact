import React, { useState, useEffect } from "react";
import "../../App.css";
import { Grid, Icon, Card, Image, GridColumn, GridRow } from "semantic-ui-react";
import image from "../../images/me.jpg";
import { useParams } from "react-router-dom";
import CvService from '../../services/cvService';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import GetSocialMediaInfo from "./GetSocialMediaInfo";
import GetExperienceInfo from "./GetExperienceInfo";
import GetEducationInfo from "./GetEducationInfo";
import GetSkillInfo from "./GetSkillInfo";
import GetLanguageInfo from "./GetLanguageInfo";

export default function CvDetails() {
    let { id } = useParams();
    let cvService = new CvService();

    const [cvs, setCvs] = useState([]);

    useEffect(() => {
        cvService.getByCvId(id).then((result) => {
            console.log(result.data.data);
            setCvs(result.data.data);
        });
    }, [id]);
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow>
                    <GridColumn width={5}>
                        <h2>
                            {[cvs].map(cv => (
                                <Card key={cv.cvId}>
                                    <Image src={image} />
                                    <Card.Content >
                                        <Card.Header>
                                            <h2><Icon name="user circle" color="black" size="large" /> {cv.jobSeeker?.firstname + "-" + cv.jobSeeker?.lastname}</h2>
                                            <h3>{cv.coverLetter}</h3>
                                        </Card.Header>
                                    </Card.Content>
                                    <Card.Content className="black">
                                        <ListItem>
                                            <ListItemAvatar>
                                                <Avatar>
                                                    <Icon name="birthday" />
                                                </Avatar>
                                            </ListItemAvatar>
                                            <ListItemText primary="Birth Year" secondary={cv.jobSeeker?.birthYear} />
                                        </ListItem>
                                        <Divider />
                                        <ListItem>
                                            <ListItemAvatar>
                                                <Avatar>
                                                    <Icon name="mail" />
                                                </Avatar>
                                            </ListItemAvatar>
                                            <ListItemText primary="E-Mail" secondary={cv.jobSeeker?.email} />
                                        </ListItem>
                                        <Divider /> <br />
                                        <GetSocialMediaInfo />
                                    </Card.Content>
                                </Card>
                            ))}
                        </h2>
                    </GridColumn>
                    <Grid.Column width={11}>
                        <GetExperienceInfo />
                        <Divider />
                        <GetEducationInfo />
                        <Divider />
                        <GetSkillInfo />
                        <Divider />
                        <GetLanguageInfo />
                    </Grid.Column>
                </GridRow>
            </Grid>
        </div>
    );
}