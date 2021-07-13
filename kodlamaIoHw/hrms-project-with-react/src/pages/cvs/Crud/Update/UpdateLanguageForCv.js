import React from 'react';
import { Button, Input, Card, Form } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import LanguageService from '../../../../services/languageService';

export default function UpdateLanguageForCv() {
    let languageService = new LanguageService();

    const validationSchema = Yup.object({
      languageName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a language name"),
      languageLevelNumber: Yup.number().required("Please enter the number of language level for your cv."),
      createdAt: Yup.date().default(() => new Date()),
    });
  
    const formik = useFormik({
      initialValues: {
        languageName: "",
        languageLevelNumber: "",
        createdAt: new Date(),
        languageId: "",
        cvId: "",
      },
      validationSchema: validationSchema,
      onSubmit: (values) => {
          values.languageId = parseInt(3);
        console.log("values: " + values)
        values.cvId = parseInt(11);
  
        languageService.updateLanguages(values).then((result) => console.log(result)).then(swal({
          title: "Succeed!",
          text: "Language is updated!",
          icon: "success",
          button: "Ok"
        }).then(function () { window.location.reload() }));
      },
    });
  
    return (
      <div>
        <Card color="orange" fluid>
          <Card.Content header='Update Language' />
          <Card.Content>
            <Form color="orange" onSubmit={formik.handleSubmit}>
              <Form.Field style={{ marginBottom: "1em" }}>
                <Input
                  style={{ width: "100%" }}
                  error={Boolean(formik.errors.languageName).toString()}
                  onChange={formik.handleChange}
                  value={formik.values.languageName}
                  onBlur={formik.handleBlur}
                  name="languageName"
                  placeholder="Language Name"
                />
                {formik.errors.languageName && formik.touched.languageName && (
                  <div className={"ui pointing red basic label"}>
                    {formik.errors.languageName}
                  </div>
                )}
              </Form.Field>
              <Form.Field>
                <Input
                  style={{ width: "100%" }}
                  id="languageLevelNumber"
                  name="languageLevelNumber"
                  error={Boolean(formik.errors.languageLevelNumber)}
                  onChange={formik.handleChange}
                  value={formik.values.languageLevelNumber}
                  onBlur={formik.handleBlur}
                  type="number"
                  placeholder="Language Level Number"
                />
                {formik.errors.languageLevelNumber && formik.touched.languageLevelNumber && (
                  <div className={"ui pointing red basic label"}>
                    {formik.errors.languageLevelNumber}
                  </div>
                )}
              </Form.Field>
              <Form.Field>
                    <Input
                      style={{ width: "30%" }}
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
              </Form.Field>
              <Button
                animated
                content="Update"
                labelPosition="right"
                icon="edit"
                color="orange"
                type="submit"
              />
            </Form>
          </Card.Content>
        </Card>
      </div>
    );
  }