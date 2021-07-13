import React, { useState, useEffect } from 'react'
import ProgrammingSkillService from '../services/programmingSkillService'
import { useParams } from "react-router-dom";
import { Grid, Icon } from "semantic-ui-react";
import "../App.css";

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

    </div>
)
}
