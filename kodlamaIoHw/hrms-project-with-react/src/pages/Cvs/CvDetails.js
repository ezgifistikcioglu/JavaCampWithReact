import React, { useEffect, useState } from 'react'
import { useParams } from "react-router";
import { Header, Icon, Table, Button, Grid, Card } from "semantic-ui-react";
import { Link } from "react-router-dom";
import AdvertisementService from '../../services/advertisementService';


export default function CvDetails() {
    let { id } = useParams();

    const [advertisementDetails, setAdvertisementDetails] = useState({});

    useEffect(() => {
        let advertisementService = new AdvertisementService();
        advertisementService.findById(id).then((result) => setAdvertisementDetails(result.data.data));
    }, [id]);

    return (
        <div>
            <Card fluid color={"violet"}>
                <Card.Content header="jobDescription" />
                <Card.Content>{advertisementDetails.jobDescription}</Card.Content>
            </Card>
            <Grid centered stackable>
                <Grid.Row>
                    <Grid.Column width={6}>
                        <Table celled color={"violet"} stackable>
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell>Employer</Table.HeaderCell>
                                    <Table.HeaderCell>Infos</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>

                            <Table.Body>
                                <Table.Row textAlign={"left"}>
                                    <Table.Cell>
                                        <Header as="h4" image>
                                            <Header.Content>
                                                <Icon name="building" />
                            Company Name
                          </Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{advertisementDetails.employer?.companyName}</Table.Cell>
                                </Table.Row>

                                <Table.Row textAlign={"left"}>
                                    <Table.Cell>
                                        <Header as="h4" image>
                                            <Header.Content>
                                                <Icon name="mail" />
                            Email
                          </Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{advertisementDetails.employer?.email}</Table.Cell>
                                </Table.Row>

                                <Table.Row textAlign={"left"}>
                                    <Table.Cell>
                                        <Header as="h4" image>
                                            <Header.Content>
                                                <Icon name="phone" />
                            Phone Number
                          </Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{advertisementDetails.employer?.telephoneNumber}</Table.Cell>
                                </Table.Row>

                                <Table.Row textAlign={"left"}>
                                    <Table.Cell>
                                        <Header as="h4" image>
                                            <Header.Content>
                                                <Icon name="world" />
                            Website
                          </Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>{advertisementDetails.employer?.webAddress}</Table.Cell>
                                </Table.Row>

                                <Table.Row textAlign={"left"}>
                                    <Table.Cell>
                                        <Header as="h4" image>
                                            <Header.Content>
                                                <Icon name="list ul" />
                            Details
                          </Header.Content>
                                        </Header>
                                    </Table.Cell>
                                    <Table.Cell>
                                        <Button
                                            animated
                                            color="orange"
                                            as={Link}
                                            to={`/employers/${advertisementDetails.employer?.userId}`}
                                        >
                                            <Button.Content visible>Go to details</Button.Content>
                                            <Button.Content hidden>
                                                <Icon name="arrow right" />
                                            </Button.Content>
                                        </Button>
                                    </Table.Cell>
                                </Table.Row>
                            </Table.Body>
                        </Table>
                    </Grid.Column>
                    <Grid.Column width={10}>
                        <Table celled fixed singleLine color={"violet"}>
                            <Table.Header>
                                <Table.Row>
                                    <Table.HeaderCell>Job Advertisement Details</Table.HeaderCell>
                                    <Table.HeaderCell>Infos</Table.HeaderCell>
                                </Table.Row>
                            </Table.Header>

                            <Table.Body>
                                <Table.Row>
                                    <Table.Cell>Job Position</Table.Cell>
                                    <Table.Cell>{advertisementDetails.position?.jobPositionName}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>City</Table.Cell>
                                    <Table.Cell>{advertisementDetails.city?.cityName}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Working Type</Table.Cell>
                                    <Table.Cell>{advertisementDetails.typeOfWorkFeature?.workTypeName}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Working Time</Table.Cell>
                                    <Table.Cell>{advertisementDetails.workTimeFeature?.workTimeName}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Minimum Salary</Table.Cell>
                                    <Table.Cell>{advertisementDetails.minSalary} ₺</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Maximum Salary</Table.Cell>
                                    <Table.Cell>{advertisementDetails.maxSalary} ₺</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Open Position</Table.Cell>
                                    <Table.Cell>{advertisementDetails.numberOfOpenPosition}</Table.Cell>
                                </Table.Row>

                                <Table.Row>
                                    <Table.Cell>Application Deadline</Table.Cell>
                                    <Table.Cell>{(
                                        (new Date(advertisementDetails.applicationDeadline).getTime() -
                                            new Date(Date.now()).getTime()) /
                                        86400000)
                                        .toString().split(".", 1)}{" "} days</Table.Cell>
                                </Table.Row>
                            </Table.Body>
                        </Table>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </div>
    );
}
