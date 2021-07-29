import React, { useEffect, useState } from 'react'
import { useParams } from "react-router";
import { Button, Input, Card, Grid, Form } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import EducationService from '../../../../services/educationService';

export default function AddEducationForCv() {
  let { id } = useParams();

  let educationService = new EducationService();
  const [educations, setEducations] = useState({});

  
  useEffect(() => {
    educationService.getByCvId(id).then((result) => setEducations(result.data.data));
}, [id]);

  const validationSchema = Yup.object({
    schoolName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a school name"),
    schoolDepartmentName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a school department name"),
    schoolGraduationDate: Yup.date().nullable().required("Please enter the graduation date"),
    schoolStartDate: Yup.date().required("Please enter the school start date"),
    createdAt: Yup.date().default(() => new Date()),
  });

  const formik = useFormik({
    initialValues: {
      schoolName: "",
      schoolDepartmentName: "",
      schoolGraduationDate: "",
      schoolStartDate: "",
      createdAt: new Date(),
      cvId: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log("values: " + values)
      values.cvId = parseInt(id);

      educationService.addEducations(values).then((result) => console.log(result)).then(swal({
        title: "Succeed!",
        text: "Education is added!",
        icon: "success",
        button: "Ok"
      }).then(function () { window.location.reload() }));
    },
  });

  return (
    <div>
      <Card color="orange" fluid>
        <Card.Content header='Add Education Knowledge' />
        <Card.Content>
          <Form color="orange" onSubmit={formik.handleSubmit}>
            <Form.Field style={{ marginBottom: "1em" }}>
              <Input
                style={{ width: "100%" }}
                error={Boolean(formik.errors.schoolName).toString()}
                value={formik.values.schoolName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                name="schoolName"
                placeholder="Your University Name"
              />
              {formik.errors.schoolName && formik.touched.schoolName && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.schoolName}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Input
                style={{ minHeight: 30 }}
                error={Boolean(formik.errors.schoolDepartmentName).toString()}
                value={formik.values.schoolDepartmentName}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                name="schoolDepartmentName"
                placeholder="Your University Department"
              />
              {formik.errors.schoolDepartmentName && formik.touched.schoolDepartmentName && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.schoolDepartmentName}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Grid stackable>
                <Grid.Column width={4}>
                  <Input
                    style={{ width: "100%" }}
                    error={(formik.errors.schoolStartDate)}
                    onChange={formik.handleChange}
                    value={formik.values.schoolStartDate}
                    onBlur={formik.handleBlur}
                    name="schoolStartDate"
                    type="datetime-local"
                    placeholder="schoolStartDate"
                  />
                  {formik.errors.schoolStartDate && formik.touched.schoolStartDate && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.schoolStartDate}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    error={(formik.errors.schoolGraduationDate)}
                    onChange={formik.handleChange}
                    value={formik.values.schoolGraduationDate}
                    onBlur={formik.handleBlur}
                    name="schoolGraduationDate"
                    type="datetime-local"
                    placeholder="schoolGraduationDate"
                  />
                  {formik.errors.schoolGraduationDate && formik.touched.schoolGraduationDate && (
                    <div className={"ui pointing red basic label"}>
                      {formik.errors.schoolGraduationDate}
                    </div>
                  )}
                </Grid.Column>
                <Grid.Column width={6}>
                  <Input
                    style={{ width: "100%" }}
                    error={(!!formik.errors.createdAt)}
                    value={new Date().toUTCString()}
                    onBlur={formik.handleBlur}
                    onChange={formik.handleChange}
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
