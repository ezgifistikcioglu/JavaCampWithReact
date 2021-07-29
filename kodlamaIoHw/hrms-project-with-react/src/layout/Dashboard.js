import React from 'react'
import JobAdvertisementList from '../pages/Lists/JobAdvertisementList'
import { Route } from 'react-router'
import { Grid, GridColumn, GridRow } from 'semantic-ui-react'
import Categories from './Categories'
import CvList from '../pages/Lists/CvList'
import EmployerList from '../pages/Lists/EmployerList'
import JobSeekerList from '../pages/Lists/JobSeekerList'
import JobPositionList from '../pages/Lists/JobPositionList'
import Settings from './Settings'
import AddAdvertisement from '../pages/AddAdvertisement'
import AdvertisementDetails from '../pages/Details/AdvertisementDetails'
import EmployeeLogin from '../pages/Authentications/EmployeeRegister'
import EmployerLogin from '../pages/Authentications/EmployerRegister'
import JobSeekerRegister from '../pages/Authentications/JobSeekerRegister'
import OpenAndApprovedAdvertisementList from '../pages/Lists/OpenAndApprovedAdvertisementList'
import LoginPage from '../pages/Authentications/LoginPage'
import AddCv from '../pages/cvs/AddCv'
import CvDetails from '../pages/cvs/CvDetails'
import AddSocialMediaForCv from '../pages/cvs/Crud/Add/AddSocialMediaForCv'
import AddEducationForCv from '../pages/cvs/Crud/Add/AddEducationForCv'
import AddLanguageForCv from '../pages/cvs/Crud/Add/AddLanguageForCv'
import AddProgrammingSkillForCv from '../pages/cvs/Crud/Add/AddProgrammingSkillForCv'
import AddWorkExperienceForCv from '../pages/cvs/Crud/Add/AddWorkExperienceForCv'
import UpdateEducationForCv from '../pages/cvs/Crud/Update/UpdateEducationForCv'
import UpdateLanguageForCv from '../pages/cvs/Crud/Update/UpdateLanguageForCv'
import UpdateProgrammingSkillForCv from '../pages/cvs/Crud/Update/UpdateProgrammingSkillForCv'
import UpdateSocialMediaForCv from '../pages/cvs/Crud/Update/UpdateSocialMediaForCv'
import UpdateWorkExperienceForCv from '../pages/cvs/Crud/Update/UpdateWorkExperienceForCv'
import SystemEmployeeList from '../pages/Lists/SystemEmployeeList'
import SystemEmployeeDetails from '../pages/Details/SystemEmployeeDetails'


export default function Dashboard() {
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow> 
                    <GridColumn width={2}>
                        <Categories />
                    </GridColumn>
                    <Grid.Column width = {14}>
                        <Route exact path= "/" component={OpenAndApprovedAdvertisementList}/>
                        <Route exact path= "/advertisementLists" component={JobAdvertisementList}/>
                        <Route exact path= "/positions" component={JobPositionList}/>
                        <Route exact path= "/employers" component={EmployerList}/>
                        <Route exact path= "/jobSeekers" component={JobSeekerList}/>
                        <Route exact path= "/systemEmployees" component={SystemEmployeeList}/>
                        <Route exact path= "/cvs" component={CvList}/>
                        <Route exact path= "/settings" component={Settings}/>
                        <Route exact path= "/advertisements" component={AddAdvertisement}/>
                        <Route exact path="/advertisementDetails/:id" component={AdvertisementDetails} />
                        <Route exact path="/systemEmployeeDetails/:id" component={SystemEmployeeDetails} />
                        <Route exact path="/employeeRegister" component={EmployeeLogin} />
                        <Route exact path="/employerRegister" component={EmployerLogin} />
                        <Route exact path="/jobSeekerRegister" component={JobSeekerRegister} />
                        <Route exact path="/login" component={LoginPage} />
                        <Route exact path="/addCv" component={AddCv} />
                        <Route exact path="/cvDetails/:id" component={CvDetails} />
                        {/* CRUD Add */}
                        <Route exact path="/addEducationForCv" component={AddEducationForCv} />
                        <Route exact path="/addLanguageForCv" component={AddLanguageForCv} />
                        <Route exact path="/addProgrammingSkillForCv" component={AddProgrammingSkillForCv} />
                        <Route exact path="/addSocialMediaForCv" component={AddSocialMediaForCv} />
                        <Route exact path="/addWorkExperienceForCv" component={AddWorkExperienceForCv} />
                        {/* CRUD Update */}
                        <Route exact path="/updateEducationForCv" component={UpdateEducationForCv} />
                        <Route exact path="/updateLanguageForCv" component={UpdateLanguageForCv} />
                        <Route exact path="/updateProgrammingSkillForCv" component={UpdateProgrammingSkillForCv} />
                        <Route exact path="/updateSocialMediaForCv" component={UpdateSocialMediaForCv} />
                        <Route exact path="/updateWorkExperienceForCv" component={UpdateWorkExperienceForCv} />

                    </Grid.Column>
                </GridRow>
            </Grid>
        </div>
    )
}
