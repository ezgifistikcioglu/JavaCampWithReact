import React, { useState, useEffect } from 'react'
import { Grid, Icon, Feed, Button } from "semantic-ui-react";
import ProgrammingSkillService from '../../services/programmingSkillService';
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import getRating from "../../utilities/cvs/getRating";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";
import AddProgrammingSkillForCv from './Crud/Add/AddProgrammingSkillForCv';
import CustomizedDialogs from '../../utilities/cvs/components/dialog';
import UpdateProgrammingSkillForCv from './Crud/Update/UpdateProgrammingSkillForCv';

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
                    <div style={{ justifyContent: "flex-end", display: "flex" }}>
                        <CustomizedDialogs color="green" icon="add">
                            <AddProgrammingSkillForCv />
                        </CustomizedDialogs>
                    </div>
                    <Divider />
                </h2>
                <Feed>
                    {skills?.map(skill => (
                        <Feed.Event
                            key={skill.id}
                            icon={getBulletIcon()}
                            style={{ marginBottom: "0.5em" }}
                            summary={skill.programmingName}
                            extraText={
                                <div> {getRating(skill.programmingSkillLevel)} <br /> <div style={{ justifyContent: "flex-end", display: "flex" }}>
                                    <CustomizedDialogs color="blue" icon="edit">
                                        <UpdateProgrammingSkillForCv />
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
