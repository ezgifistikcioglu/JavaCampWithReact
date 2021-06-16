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


export default function Dashboard() {
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow> 
                    <GridColumn width={4}>
                        <Categories />
                    </GridColumn>
                    <Grid.Column width = {12}>
                        <Route exact path= "/" component={JobAdvertisementList}/>
                        <Route exact path= "/positions" component={JobPositionList}/>
                        <Route  path= "/employers" component={EmployerList}/>
                        <Route  path= "/jobSeekers" component={JobSeekerList}/>
                        <Route  path= "/cvs" component={CvList}/>
                        <Route  path= "/settings" component={Settings}/>
                        <Route  path= "/advertisements" component={AddAdvertisement}/>
                    </Grid.Column>
                </GridRow>
            </Grid>
        </div>
    )
}
