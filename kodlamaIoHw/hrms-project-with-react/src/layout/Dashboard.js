import React from 'react'
import JobAdvertisementList from '../pages/JobAdvertisementList'
import { Route } from 'react-router'
import { Grid, GridColumn, GridRow } from 'semantic-ui-react'
import Categories from './Categories'
import CvList from '../pages/CvList'
import EmployerList from '../pages/EmployerList'
import JobSeekerList from '../pages/JobSeekerList'
import JobPositionList from '../pages/JobPositionList'
import Settings from './Settings'
import AddAdvertisement from '../pages/AddAdvertisement'
import AdvertisementDetails from '../pages/AdvertisementDetails'
import EmployeeLogin from '../pages/Authentications/EmployeeRegister'
import EmployerLogin from '../pages/Authentications/EmployerRegister'
import JobSeekerLogin from '../pages/Authentications/JobSeekerRegister'
import OpenAndApprovedAdvertisementList from '../pages/OpenAndApprovedAdvertisementList'
import LoginPage from '../pages/Authentications/LoginPage'


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
                        <Route exact path= "/cvs" component={CvList}/>
                        <Route exact path= "/settings" component={Settings}/>
                        <Route exact path= "/advertisements" component={AddAdvertisement}/>
                        <Route exact path="/advertisementDetails/:id" component={AdvertisementDetails} />
                        <Route exact path="/employeeRegister" component={EmployeeLogin} />
                        <Route exact path="/employerRegister" component={EmployerLogin} />
                        <Route exact path="/jobSeekerRegister" component={JobSeekerLogin} />
                        <Route exact path="/login" component={LoginPage} />
                    </Grid.Column>
                </GridRow>
            </Grid>
        </div>
    )
}
