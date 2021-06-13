import React from 'react'
import JobAdvertisementList from '../pages/JobAdvertisementList'
import { Grid, GridColumn, GridRow } from 'semantic-ui-react'
import Categories from './Categories'
import CvList from '../pages/CvList'
import EmployerList from '../pages/EmployerList'
import JobSeekerList from '../pages/JobSeekerList'
import JobPositionList from '../pages/JobPositionList'


export default function Dashboard() {
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow>
                    <GridColumn width={4}>
                        <Categories />
                    </GridColumn>
                    <GridColumn width={6}>
                        <JobAdvertisementList />
                    </GridColumn>
                    <GridColumn width={6}>
                        <JobPositionList/>
                    </GridColumn>
                </GridRow>
                <GridRow >
                <GridColumn width={7}>
                        <EmployerList/>
                    </GridColumn>
                    <GridColumn width={9}>
                        <JobSeekerList/>
                    </GridColumn>
                </GridRow>
                <GridRow centered>
                <GridColumn width={10}>
                        <CvList/>
                    </GridColumn>

                </GridRow>
            </Grid>
        </div>
    )
}
