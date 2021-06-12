import React from 'react'
import JobAdvertisementList from '../pages/JobAdvertisementList'
import { Grid, GridColumn, GridRow } from 'semantic-ui-react'
import Categories from './Categories'


export default function Dashboard() {
    return (
        <div>
            <Grid columns={2} divided>
                <GridRow>
                    <GridColumn width={4}>
                        <Categories />
                    </GridColumn>
                    <GridColumn width={12}>
                        <JobAdvertisementList />
                    </GridColumn>
                </GridRow>
            </Grid>
        </div>
    )
}
