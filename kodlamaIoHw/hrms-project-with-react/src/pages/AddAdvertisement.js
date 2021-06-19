import React, { useState, useEffect } from 'react';
import AdvertisementService from '../services/advertisementService';
import { Button, Dropdown, Input, TextArea, Card, Form, Grid } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import WorkTypeService from '../services/workTypeService';
import WorkTimeService from '../services/workTimeService';
import JobPositionService from '../services/jobPositionService';
import CityService from '../services/cityService';


export default function AddAdvertisement() {
  let advertisementService = new AdvertisementService();

  const [workTypeServices, setWorkTypeServices] = useState([]);
  const [workTimeServices, setWorkTimeServices] = useState([]);
  const [jobPositionServices, setJobPositionServices] = useState([]);
  const [cityServices, setCityServices] = useState([]);

  useEffect(() => {

    let workTypeService = new WorkTypeService();
    let workTimeService = new WorkTimeService();
    let jobPositionService = new JobPositionService();
    let cityService = new CityService();

    workTypeService.getworkTypes().then(result => setWorkTypeServices(result.data.data));
    workTimeService.getworkTimes().then(result => setWorkTimeServices(result.data.data));
    jobPositionService.getJobPositions().then(result => setJobPositionServices(result.data.data));
    cityService.getCities().then(result => setCityServices(result.data.data));
  }, [])


  const jobPositionOptions = jobPositionServices.map((jobPositionService, keyIndex) => ({
    key: keyIndex,
    text: jobPositionService.jobPositionName,
    value: jobPositionService.id,
  }));

  const cityOptions = cityServices.map((cityService, keyIndex) => ({
    key: keyIndex,
    text: cityService.cityName,
    value: cityService.id,
  }));

  const workTypeOptions = workTypeServices.map((workTypeService, keyIndex) => ({
    key: keyIndex,
    text: workTypeService.workTypeName,
    value: workTypeService.workTypeId,
  }));

  const workTimeOptions = workTimeServices.map((workTimeService, keyIndex) => ({
    key: keyIndex,
    text: workTimeService.workTimeName,
    value: workTimeService.workTimeId,
  }));

  const validationSchema = Yup.object({
    jobPositionId: Yup.number().required("Select a position please"),
    jobDescription: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a description"),
    minSalary: Yup.number().required("Please enter the minimum salary you have determined."),
    maxSalary: Yup.number().required("Please enter the maximum salary you have determined."),
    applicationDeadline: Yup.date().nullable().required("Please enter the deadline for the posting"),
    createdDate: Yup.date().default(() => new Date()),
    numberOfOpenPosition: Yup.number().required("Please enter the number of open positions for your ad."),
    workTypeId: Yup.string().required("Please select your work type"),
    workTimeId: Yup.string().required("Please select your work time"),
    cityId: Yup.string().required("Please select the  city for job posting"),
  });

  const handleValue = (value, fieldName) => {
    formik.setFieldValue(fieldName, value);
  }
  const formik = useFormik({
    initialValues: {
      jobPositionId: "",
      jobDescription: "",
      minSalary: "",
      maxSalary: "",
      applicationDeadline: "",
      createdDate: new Date(),
      numberOfOpenPosition: "",
      workTypeId: "",
      workTimeId: "",
      cityId: "",
      employerId: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      values.employerId = 16;
      values.jobPositionId = parseInt(values.jobPositionId);
      values.workTimeId = parseInt(values.workTimeId);
      values.workTypeId = parseInt(values.workTypeId);
      values.cityId = parseInt(values.cityId);
      values.minSalary = parseInt(values.minSalary);
      values.maxSalary = parseInt(values.maxSalary);
      values.numberOfOpenPosition = parseInt(values.numberOfOpenPosition);

      advertisementService.addAdvertisements(values).then((result) => console.log(result)).then(swal({
        title: "Succeed!",
        text: "Job Advertisement is added!",
        icon: "success",
        button: "Ok"
      }).then(function () { window.location.reload() }));;
    },
  });

  return (
    <div>
      <Card color="violet" fluid>
        <Card.Content header='Add Job Advertisement' />
        <Card.Content>
          <Form color="violet" onSubmit={formik.handleSubmit}>
            <Form.Field style={{ marginBottom: "1em" }}>
              <Dropdown
                clearable
                item
                placeholder="Job Position"
                search
                selection
                onChange={(_event, data) =>
                  handleValue(data.value, "jobPositionId")
                }
                onBlur={formik.onBlur}
                id="jobPositionId"
                value={formik.values.jobPositionId}
                options={jobPositionOptions}
              />
              {formik.errors.jobPositionId && formik.touched.jobPositionId && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.jobPositionId}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Dropdown
                clearable
                item
                placeholder="City"
                search
                selection
                onChange={(_event, data) =>
                  handleValue(data.value, "cityId")
                }
                onBlur={formik.onBlur}
                id="cityId"
                value={formik.values.cityId}
                options={cityOptions}
              />
              {formik.errors.cityId && formik.touched.cityId && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.cityId}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Dropdown
                clearable
                item
                placeholder="Working Type"
                search
                selection
                onChange={(_event, data) =>
                  handleValue(data.value, "workTypeId")
                }
                onBlur={formik.onBlur}
                id="workTypeId"
                value={formik.values.workTypeId}
                options={workTypeOptions}
              />
              {formik.errors.workTypeId && formik.touched.workTypeId && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.workTypeId}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Dropdown
                clearable
                item
                placeholder="Working Time"
                search
                selection
                onChange={(_event, data) =>
                  handleValue(data.value, "workTimeId")
                }
                onBlur={formik.onBlur}
                id="workTimeId"
                value={formik.values.workTimeId}
                options={workTimeOptions}
              />
              {formik.errors.workTimeId && formik.touched.workTimeId && (
                <div className={"ui pointing red basic label"}>{formik.errors.workTimeId}</div>
              )}
            </Form.Field>
            <Form.Field>
              <Grid stackable>
                <Grid.Column width={8}>
                  <Input
                    style={{ width: "100%" }}
                    type="number"
                    placeholder="Minimum Salary"
                    value={formik.values.minSalary}
                    name="minSalary"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  >
                  </Input>
                  {formik.errors.minSalary && formik.touched.minSalary && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.minSalary}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={8}>
                  <Input
                    style={{ width: "100%" }}
                    type="number"
                    placeholder="Maximum Salary"
                    value={formik.values.maxSalary}
                    name="maxSalary"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                  >
                  </Input>
                  {formik.errors.maxSalary && formik.touched.maxSalary && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.maxSalary}
                    </div>
                  )}
                </Grid.Column>
              </Grid>
            </Form.Field>

            <Form.Field>
              <Grid stackable>
                <Grid.Column width={4}>
                  <Input
                    style={{ width: "100%" }}
                    id="numberOfOpenPosition"
                    name="numberOfOpenPosition"
                    error={Boolean(formik.errors.numberOfOpenPosition)}
                    onChange={formik.handleChange}
                    value={formik.values.numberOfOpenPosition}
                    onBlur={formik.handleBlur}
                    type="number"
                    placeholder="numberOfOpenPosition"
                  />
                  {formik.errors.numberOfOpenPosition && formik.touched.numberOfOpenPosition && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.numberOfOpenPosition}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    error={Boolean(formik.errors.applicationDeadline)}
                    onChange={(_event, data) =>
                      handleValue(data.value, "applicationDeadline")
                    }
                    value={formik.values.applicationDeadline}
                    onBlur={formik.handleBlur}
                    name="applicationDeadline"
                    type="datetime-local"
                    placeholder="applicationDeadline"
                  />
                  {formik.errors.applicationDeadline && formik.touched.applicationDeadline && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.applicationDeadline}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    value={new Date().toUTCString()}
                    type="date-now"
                    placeholder="createdDate"
                  />
                </Grid.Column>
              </Grid>
            </Form.Field>

            <Form.Field>
              <TextArea
                placeholder="jobDescription"
                style={{ minHeight: 150 }}
                error={Boolean(formik.errors.jobDescription).toString()}
                value={formik.values.jobDescription}
                name="jobDescription"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
              />
              {formik.errors.jobDescription && formik.touched.jobDescription && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.jobDescription}
                </div>
              )}
            </Form.Field>
            <Button
              animated
              content="Add"
              labelPosition="right"
              icon="add"
              color="orange"
              type="submit"
              style={{ marginLeft: "20px" }}
            />
          </Form>
        </Card.Content>
      </Card>
    </div>
  );

}
