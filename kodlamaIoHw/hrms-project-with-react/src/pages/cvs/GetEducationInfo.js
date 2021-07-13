import React, { useState, useEffect } from 'react'
import "../../App.css";
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import { Grid, Icon, Feed } from "semantic-ui-react";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";
import EducationServise from '../../services/educationService'

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
                    <Divider/>
                </h2>
                <Feed>
                    {educations?.map(ed => (
                        <Feed.Event
                            key={ed.educationId}
                            icon={getBulletIcon()}
                            date={ed.schoolStartDate}
                            summary={ed.schoolName}
                            extraText={ed.schoolDepartmentName}
                            style={{ marginBottom: "1em" }}
                        />
                    ))}
                </Feed>
            </Grid.Row>
        </div>
    )
}
