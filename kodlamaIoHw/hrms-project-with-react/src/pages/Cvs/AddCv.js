import React, { useState, useEffect } from 'react';
import { Button, Input, Card, Form } from "semantic-ui-react";
import { useParams } from "react-router-dom";
import { NavLink } from "react-router-dom";
import AddEducationForCv from './AddEducationForCv'
import AddLanguageForCv from './AddLanguageForCv'
import AddProgrammingSkillForCv from './AddProgrammingSkillForCv'
import AddSocialMediaForCv from './AddSocialMediaForCv'
import AddWorkExperienceForCv from './AddWorkExperienceForCv'
import CvService from '../../services/cvService';


export default function AddCv() {
  let { id } = useParams();
  const [cvs, setCvs] = useState({});

  useEffect(() => {
      let cvService = new CvService();
      cvService.getByCvId(id).then((result) => setCvs(result.data.data));
  }, [id]);

  return (
    <div>
      <Card color="black" fluid>
        <Card.Content>
        <Card.Header style={{ color: "orange" }}>CV GENERATOR</Card.Header>
        </Card.Content>
        <Card.Content  style={{
            backgroundImage: `url(https://cdn.pixabay.com/photo/2017/05/13/15/18/dear-2309801_1280.jpg)`,
            backgroundSize: "cover",
          }}>
          <Form color="orange" >
            <Form.Field style={{ marginBottom: "1em" }}>
             <AddWorkExperienceForCv/>
            </Form.Field>
            <Form.Field>
              <AddProgrammingSkillForCv/>
            </Form.Field>
            <Form.Field>
                <AddLanguageForCv/> 
            </Form.Field>
            <Form.Field>
                <AddEducationForCv/> 
            </Form.Field>
            <Form.Field>
                <AddSocialMediaForCv/> 
            </Form.Field>
            <Button
            as={NavLink} to={`/cvDetails/${cvs.cvId}`} 
              animated
              content="Show New Cv"
              labelPosition="right"
              icon="eye"
              color="orange"
              type="submit"
            />
          </Form>
        </Card.Content>
      </Card>
    </div>
  );
}
