import React, { useState, useEffect } from 'react'
import "../../App.css";
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import { Grid, Icon, Feed, Button } from "semantic-ui-react";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";
import EducationServise from '../../services/educationService'
import CustomizedDialogs from '../../utilities/cvs/components/dialog';
import AddEducationForCv from './Crud/Add/AddEducationForCv'
import UpdateEducationForCv from './Crud/Update/UpdateEducationForCv';
import { getByDisplayValue } from '@testing-library/react';

export default function GetEducationInfo() {
    let educationServise = new EducationServise();
    let { id } = useParams();

    const [educations, setEducations] = useState([]);

    useEffect(() => {
        educationServise.getByCvId(id).then((result) => {
            setEducations(result.data.data);
        });
    }, [educations]);
    
    return (
        <div>
            <Grid.Row>
                <h2>
                    <Icon name="student" color="black" />
                    Education
                    <div style={{ justifyContent: "flex-end", display: "flex" }}>
                        <CustomizedDialogs color="green" icon="add">
                            <AddEducationForCv />
                        </CustomizedDialogs>
                    </div>
                    <Divider />
                </h2>
                <Feed>
                    {educations?.map(ed => (
                        <Feed.Event
                            key={ed.educationId}
                            style={{ marginBottom: "1em" }}
                            icon={getBulletIcon()}
                            date={ed.schoolStartDate}
                            summary={ed.schoolName}
                            extraText={
                                <div> {ed.schoolDepartmentName} <div style={{ justifyContent: "flex-end", display: "flex" }}>
                                    <CustomizedDialogs color="blue" icon="edit">
                                        <UpdateEducationForCv />
                                    </CustomizedDialogs>
                                    <Button size='mini' icon="delete" color="red" onClick={{}} compact="true" />
                                </div>
                                    <Divider style={{ background: 'black' }} />
                                </div>}
                        />
                    ))}
                </Feed>
            </Grid.Row>
        </div>
    )
}
