import React, { useState, useEffect } from 'react'
import { Table, Menu, Icon } from 'semantic-ui-react'
import JobSeekerService from '../services/jobSeekerService';

export default function JobSeekerList(){
    const [jobSeekers, setJobSeekers] = useState([]);

    useEffect(() => {
      let  jobSeekerService = new JobSeekerService();
      jobSeekerService.getJobSeekers().then(result=>setJobSeekers(result.data.data))
    }, [])
    return (
        <div>
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Email</Table.HeaderCell>
                        <Table.HeaderCell>First Name</Table.HeaderCell>
                        <Table.HeaderCell>Last Name</Table.HeaderCell>
                        <Table.HeaderCell>TC Number</Table.HeaderCell>
                        <Table.HeaderCell>Birth Year</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    {
                        jobSeekers.map(jobSeeker => (
                            <Table.Row key={jobSeeker.id}>
                                <Table.Cell>{jobSeeker.email}</Table.Cell>
                                <Table.Cell>{jobSeeker.firstname}</Table.Cell>
                                <Table.Cell>{jobSeeker.lastname}</Table.Cell>
                                <Table.Cell>{jobSeeker.tcNo}</Table.Cell>
                                <Table.Cell>{jobSeeker.birthYear}</Table.Cell>
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