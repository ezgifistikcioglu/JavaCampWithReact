import React, { useState, useEffect } from 'react'
import SocialMediaService from '../../services/socialMediaService'
import { useParams } from "react-router-dom";
import { Grid, Icon } from "semantic-ui-react";
import getLink from '../../utilities/cvs/getLink';

export default function GetSocialMediaInfo() {
    let socialMediaService = new SocialMediaService();
    let { id } = useParams();
    
    const [medias, setSocialMedias] = useState([]);

    useEffect(() => {
        socialMediaService.getByCvId(id).then((result) => {
            setSocialMedias(result.data.data);
        });
    }, [medias]);

    return (
            <Grid>
                    {medias?.map(media => (
                    <span style={{ marginBottom: "0.1em" , key:{media}} }>
                      <Icon name="dot circle" size="tiny"/>
                        {getLink(media.socialMediaName, media.socialMediaUrl)}<br/>
                    </span>
                ))}              
            </Grid>
    )
}
