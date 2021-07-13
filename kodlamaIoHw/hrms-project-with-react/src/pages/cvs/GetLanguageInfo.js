import React, { useState, useEffect } from 'react'
import { Grid, Icon, Feed } from "semantic-ui-react";
import LanguageService from '../../services/languageService';
import { useParams } from "react-router-dom";
import Divider from '@material-ui/core/Divider';
import getRating from "../../utilities/cvs/getRating";
import getBulletIcon from "../../utilities/cvs/getBulletIcon";


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
                    <Divider />
                </h2> 
                 <Feed>
                    {languages?.map(language => (
                        <Feed.Event
                            key={language.languageId}
                            icon={getBulletIcon()}
                            summary={language.languageName}
                            extraText={getRating(language.languageLevelNumber)}
                            style={{ marginBottom: "0.5em" }}
                        />
                ))}
                </Feed> 
            </Grid.Row>
        </div>
    )
}