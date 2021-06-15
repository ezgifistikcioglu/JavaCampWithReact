import React, {useState, useEffect} from 'react'
import { Table, Menu, Icon } from 'semantic-ui-react'
import JobPositionService from '../services/jobPositionService'

export default function JobPositionList() {
    const [jobPositions, setJobPositions] = useState([])

    useEffect(() => {
        let jobPositionService = new JobPositionService();
        jobPositionService.getJobPositions().then(result=> setJobPositions(result.data.data))
    }, [])
    return (
        <div>
        <Table basic='very' celled collapsing>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Job Name</Table.HeaderCell>
                    <Table.HeaderCell>Position Active</Table.HeaderCell>
                    <Table.HeaderCell>Position Deleted</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {
                    jobPositions.map(jobPosition => (
                        <Table.Row key={jobPosition.id}>
                            <Table.Cell>{jobPosition.jobName}</Table.Cell>
                            <Table.Cell>{jobPosition.activePosition}</Table.Cell>
                            <Table.Cell>{jobPosition.deletedPosition}</Table.Cell>
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
