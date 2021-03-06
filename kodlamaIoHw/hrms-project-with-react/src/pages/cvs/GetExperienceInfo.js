import React, { useState, useEffect } from 'react'
import "../../App.css";
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import { Grid, Icon, Feed, Button } from "semantic-ui-react";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";
import getLink from "../../utilities/cvs/getLink";
import WorkExperienceService from '../../services/workExperienceService'
import CustomizedDialogs from "../../utilities/cvs/components/dialog";
import AddWorkExperienceForCv from './Crud/Add/AddWorkExperienceForCv';
import UpdateWorkExperienceForCv from './Crud/Update/UpdateWorkExperienceForCv';

export default function GetExperienceInfo() {
    let workExperienceService = new WorkExperienceService();
    let { id } = useParams();

    const [experiences, setExperiences] = useState([]);

    useEffect(() => {
        workExperienceService.getByCvId(id).then((result) => {
            setExperiences(result.data.data);
        });
    }, [experiences]);

    return (
        <div>
            <Grid.Row>
                <h2>
                    <Icon name="travel" color="black" />
                    Experience
                    <div style={{ justifyContent: "flex-end", display: "flex" }}>
                        <CustomizedDialogs color="green" icon="add">
                            <AddWorkExperienceForCv />
                        </CustomizedDialogs>
                    </div>
                    <Divider />
                </h2>

                <Feed>
                    {experiences?.map(exp => (
                        <Feed.Event
                            key={exp.experienceId}
                            icon={getBulletIcon()}
                            style={{ marginBottom: "0.5em" }}
                            date={exp.businessStartDate + " / " + exp.businessLeavingDate}
                            summary={
                                    <div>
                                        <b>{exp.jobName}</b> @{" "}
                                        {getLink(exp.businessName, "www.google.com")}
                                        <div style={{ justifyContent: "flex-end", display: "flex" }}>
                                            <CustomizedDialogs color="blue" icon="edit">
                                                <UpdateWorkExperienceForCv />
                                            </CustomizedDialogs>
                                            <hr/>
                                            <Button size='mini' icon="delete" color="red" onClick={{}} compact="true" />
                                        </div>
                                        <Divider style={{ background: 'black' }} />
                                    </div>
                            }
                        />
                    ))}

                </Feed>
            </Grid.Row>
        </div>
    )
}
