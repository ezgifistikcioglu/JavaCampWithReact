import React from 'react';
import { Button, Input, TextArea, Card, Grid, Form } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import { useParams } from "react-router-dom";
import WorkExperienceService from '../services/workExperienceService';

export default function AddWorkExperienceForCv() {
  let workExperienceService = new WorkExperienceService();
  const { cvId } = useParams();

  const validationSchema = Yup.object({
    businessName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a school name"),
    jobName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a school department name"),
    businessLeavingDate: Yup.date().nullable().required("Please enter the graduation date"),
    businessStartDate: Yup.date().required("Please enter the school start date"),
    createdAt: Yup.date().default(() => new Date()),
    stillWorking: Yup.bool().oneOf([true, false], 'Field must be checked'),
  });

  const handleValue = (value, fieldName) => {
    formik.setFieldValue(fieldName, value);
  }
  const formik = useFormik({
    initialValues: {
      businessName: "",
      jobName: "",
      businessLeavingDate: "",
      businessStartDate: "",
      createdAt: new Date(),
      cvId: "",
      stillWorking: ""
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log("values: " + values)
      values.cvId = parseInt(11);

      workExperienceService.addWorkExperiences(values).then((result) => console.log(result)).then(swal({
        title: "Succeed!",
        text: "Work experiences is added!",
        icon: "success",
        button: "Ok"
      }).then(function () { window.location.reload() }));
    },
  });

  return (
    <div>
      <Card color="orange" fluid>
        <Card.Content header='Add Work Experience Knowledge' />
        <Card.Content>
          <Form color="orange" onSubmit={formik.handleSubmit}>
            <Form.Field style={{ marginBottom: "1em" }}>
              <Input
                style={{ width: "100%" }}
                error={Boolean(formik.errors.businessName).toString()}
                value={formik.values.businessName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                name="businessName"
                placeholder="Your Business Name"
              />
              {formik.errors.businessName && formik.touched.businessName && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.businessName}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Input
                style={{ minHeight: 30 }}
                error={Boolean(formik.errors.jobName).toString()}
                value={formik.values.jobName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                name="jobName"
                placeholder="Job Name"
              />
              {formik.errors.jobName && formik.touched.jobName && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.jobName}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Grid stackable>
                <Grid.Column width={4}>
                  <Input
                    style={{ width: "100%" }}
                    error={(formik.errors.businessStartDate)}
                    onChange={(_event, data) =>
                      handleValue(data.value, "businessStartDate")
                    }
                    value={formik.values.businessStartDate}
                    onBlur={formik.handleBlur}
                    name="businessStartDate"
                    type="datetime-local"
                    placeholder="Business Start Date"
                  />
                  {formik.errors.businessStartDate && formik.touched.businessStartDate && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.businessStartDate}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    error={(formik.errors.businessLeavingDate)}
                    onChange={(_event, data) =>
                      handleValue(data.value, "businessLeavingDate")
                    }
                    value={formik.values.businessLeavingDate}
                    onBlur={formik.handleBlur}
                    name="businessLeavingDate"
                    type="datetime-local"
                    placeholder="Business Leaving Date"
                  />
                  {formik.errors.businessLeavingDate && formik.touched.businessLeavingDate && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.businessLeavingDate}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    error={(!!formik.errors.createdAt)}
                    value={new Date().toUTCString()}
                    onBlur={formik.handleBlur}
                    name="createdAt"
                    type="date-now"
                    placeholder="createdAt"
                  />
                  {formik.errors.createdAt && formik.touched.createdAt && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.createdAt}
                    </div>
                  )}
                </Grid.Column>
              </Grid>
            </Form.Field>
            <Form.Field>
              <Input
                style={{ width: "100%" }}
                type="boolean"
                placeholder="Is still working"
                value={formik.values.stillWorking}
                name="stillWorking"
                onChange={formik.handleChange}
                onBlur={formik.handleBlur}
              >
              </Input>
              {formik.errors.stillWorking && formik.touched.stillWorking && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.stillWorking}
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
            />
          </Form>
        </Card.Content>
      </Card>
    </div>
  );
}
