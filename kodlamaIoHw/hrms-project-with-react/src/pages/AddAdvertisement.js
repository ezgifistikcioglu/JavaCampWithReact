import React, { useState, useEffect } from 'react'
import { Button,Modal,Form,Dropdown,Input,TextArea,Table, Menu, Icon } from 'semantic-ui-react'
import AdvertisementService from '../services/advertisementService';
import { useFormik } from "formik";
import * as Yup from "yup";
import WorkTypeService from '../services/workTypeService'
import WorkTimeService from '../services/workTimeService'
import JobPositionService from '../services/jobPositionService'
import CityService from '../services/cityService'


export default function AddAdvertisement({addButton}) {
    const [advertisements, setAdvertisements] = useState([]);
    const [workTypeServices, setWorkTypeServices] = useState([]);
    const [workTimeServices, setWorkTimeServices] = useState([]);
    const [jobPositionServices, setJobPositionServices] = useState([]);
    const [jobCityServices, setCityServices] = useState([]);

    useEffect(()=>{
        let advertisementService = new AdvertisementService();
        let workTypeService = new WorkTypeService();
        let workTimeService = new WorkTimeService();
        let jobPositionService = new JobPositionService();
        let cityService = new CityService();

        advertisementService.getAdvertisements().then(result=>setAdvertisements(result.data.data));
        workTypeService.getworkTypes().then(result=>setWorkTypeServices(result.data.data));
        workTimeService.getworkTimes().then(result=>setWorkTimeServices(result.data.data));
        jobPositionService.getJobPositions().then(result=>setJobPositionServices(result.data.data));
        cityService.getCities().then(result=>setCityServices(result.data.data));
    },[])

    return (
           <div>
            <Table basic='very' celled collapsing>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>lo Description</Table.HeaderCell>
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
                                <Table.Cell>{advertisement.minSalary}</Table.Cell>
                                <Table.Cell>{advertisement.maxSalary}</Table.Cell>
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
