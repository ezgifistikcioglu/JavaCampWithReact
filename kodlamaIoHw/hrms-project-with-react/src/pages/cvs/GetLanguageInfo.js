import React, { useState, useEffect } from 'react'
import { Grid, Icon, Feed, Button } from "semantic-ui-react";
import LanguageService from '../../services/languageService';
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import getRating from "../../utilities/cvs/getRating";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";
import CustomizedDialogs from '../../utilities/cvs/components/dialog';
import AddLanguageForCv from './Crud/Add/AddLanguageForCv';
import UpdateLanguageForCv from './Crud/Update/UpdateLanguageForCv';

export default function GetLanguageInfo() {
    let languageService = new LanguageService();
    let { id } = useParams();

    const [languages, setLanguages] = useState([]);

    useEffect(() => {
        languageService.findAllByCvId(id).then((result) => {
            setLanguages(result.data.data);
        });
    }, [languages]);

    return (
        <div>
            <Grid.Row style={{ padding: "0.5em" }} >
                <h2>
                    <Icon name="language" color="black" />
                    Language
                    <div style={{ justifyContent: "flex-end", display: "flex" }}>
                        <CustomizedDialogs color="green" icon="add">
                            <AddLanguageForCv />
                        </CustomizedDialogs>
                    </div>
                    <Divider />
                </h2>
                <Feed>
                    {languages?.map(language => (
                        <Feed.Event
                            key={language.languageId}
                            icon={getBulletIcon()}
                            style={{ marginBottom: "0.5em" }}
                            summary={language.languageName}
                            extraText={
                                <div> {getRating(language.languageLevelNumber)} <br /> <div style={{ justifyContent: "flex-end", display: "flex" }}>
                                    <CustomizedDialogs color="blue" icon="edit">
                                        <UpdateLanguageForCv />
                                    </CustomizedDialogs>                                <Button size='mini' icon="delete" color="red" onClick={{}} compact="true" />
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