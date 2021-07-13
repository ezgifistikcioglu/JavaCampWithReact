import React, { useState, useEffect } from 'react'
import { Grid, Icon, Feed } from "semantic-ui-react";
import ProgrammingSkillService from '../../services/programmingSkillService';
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import getRating from "../../utilities/cvs/getRating";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";


export default function GetSkillInfo() {
    let programmingSkillService = new ProgrammingSkillService();
    let { id } = useParams();

    const [skills, setProgrammingSkills] = useState([]);

    useEffect(() => {
        programmingSkillService.findAllByCvId(id).then((result) => {
            setProgrammingSkills(result.data.data);
        });
    }, [skills]);

    return (
        <div>
            <Grid.Row style={{ padding: "0.5em" }} >
                <h2>
                    <Icon name="settings" color="black" />
                    Programming Skill
                    <Divider />
                </h2> 
                 <Feed>
                    {skills?.map(skill => (
                        <Feed.Event
                            key={skill.id}
                            icon={getBulletIcon()}
                            summary={skill.programmingName}
                            extraText={getRating(skill.programmingSkillLevel)}
                            style={{ marginBottom: "0.5em" }}
                        />
                ))}
                </Feed> 
            </Grid.Row>
        </div>
    )
}
