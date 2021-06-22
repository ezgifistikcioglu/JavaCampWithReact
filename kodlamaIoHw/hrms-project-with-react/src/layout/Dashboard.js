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
import OpenAndApprovedAdvertisementList from '../pages/OpenAndApprovedAdvertisementList'


export default function Dashboard() {
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow> 
                    <GridColumn width={4}>
                        <Categories />
                    </GridColumn>
                    <Grid.Column width = {12}>
                        <Route exact path= "/" component={OpenAndApprovedAdvertisementList}/>
                        <Route exact path= "/advertisementLists" component={JobAdvertisementList}/>
                        <Route exact path= "/positions" component={JobPositionList}/>
                        <Route exact path= "/employers" component={EmployerList}/>
                        <Route exact path= "/jobSeekers" component={JobSeekerList}/>
                        <Route exact path= "/cvs" component={CvList}/>
                        <Route exact path= "/settings" component={Settings}/>
                        <Route exact path= "/advertisements" component={AddAdvertisement}/>
                        <Route exact path="/advertisementDetails/:id" component={AdvertisementDetails} />
                    </Grid.Column>
                </GridRow>
            </Grid>
        </div>
    )
}
