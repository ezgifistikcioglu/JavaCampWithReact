import React, { useState, useEffect } from 'react'
import { Table, Menu, Icon } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService';

export default function JobAdvertisementList() {
    const [advertisements, setAdvertisements] = useState([]);

    useEffect(()=>{
        let advertisementService = new AdvertisementService()
        advertisementService.getAdvertisements().then(result=>setAdvertisements(result.data.data))
    })

    return (
        <div>
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Job Description</Table.HeaderCell>
                        <Table.HeaderCell>Min Salary</Table.HeaderCell>
                        <Table.HeaderCell>Max Salary</Table.HeaderCell>
                        <Table.HeaderCell>Open Position</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {
                        advertisements.map(advertisement => (
                            <Table.Row key={advertisement.id}>
                                <Table.Cell>{advertisement.jobDescription}</Table.Cell>
                                <Table.Cell>{advertisement.maxSalary}</Table.Cell>
                                <Table.Cell>{advertisement.minSalary}</Table.Cell>
                                <Table.Cell>{advertisement.numberOfOpenPosition}</Table.Cell>
                            </Table.Row>
                        ))
                    }
                </Table.Body>
                <Table.Footer>
                    <Table.Row>
                        <Table.HeaderCell colSpan='3'>
                            <Menu floated='right' pagination>
                                <Menu.Item as='a' icon>
                                    <Icon name='chevron left' />
                                </Menu.Item>
                                <Menu.Item as='a'>1</Menu.Item>
                                <Menu.Item as='a'>2</Menu.Item>
                                <Menu.Item as='a'>3</Menu.Item>
                                <Menu.Item as='a'>4</Menu.Item>
                                <Menu.Item as='a' icon>
                                    <Icon name='chevron right' />
                                </Menu.Item>
                            </Menu>
                        </Table.HeaderCell>
                    </Table.Row>
                </Table.Footer>
            </Table>
        </div>
    )
}
