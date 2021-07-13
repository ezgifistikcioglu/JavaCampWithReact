import React from 'react'
import { Button, Input, Card, Form } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import SocialMediaService from '../../../../services/socialMediaService';

export default function AddSocialMediaForCv() {
    let socialMediaService = new SocialMediaService();

  const validationSchema = Yup.object({
    socialMediaName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a programming skill name"),
    socialMediaUrl: Yup.string()
    .matches(
        /((https?):\/\/)?(www.)?[a-z0-9]+(\.[a-z]{2,}){1,3}(#?\/?[a-zA-Z0-9#]+)*\/?(\?[a-zA-Z0-9-_]+=[a-zA-Z0-9-%]+&?)?$/,
        'Enter correct url!'
    )
    .required('Please enter website'),
    createdAt: Yup.date().default(() => new Date()),
  });

  const formik = useFormik({
    initialValues: {
      socialMediaName: "",
      socialMediaUrl: "",
      createdAt: new Date(),
      cvId: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      console.log("values: " + values)
      values.cvId = parseInt(11);

      socialMediaService.addSocialMedias(values).then((result) => console.log(result)).then(swal({
        title: "Succeed!",
        text: "Social Media is added!",
        icon: "success",
        button: "Ok"
      }).then(function () { window.location.reload() }));
    },
  });

  return (
    <div>
      <Card color="orange" fluid>
        <Card.Content header='Add Social Media' />
        <Card.Content>
          <Form color="orange" onSubmit={formik.handleSubmit}>
            <Form.Field style={{ marginBottom: "1em" }}>
              <Input
                style={{ width: "100%" }}
                error={Boolean(formik.errors.socialMediaName).toString()}
                onChange={formik.handleChange}
                value={formik.values.socialMediaName}
                onBlur={formik.handleBlur}
                name="socialMediaName"
                placeholder="Social Media Name"
              />
              {formik.errors.socialMediaName && formik.touched.socialMediaName && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.socialMediaName}
                </div>
              )}
            </Form.Field>
            <Form.Field>
              <Input
                style={{ width: "100%" }}
                id="socialMediaUrl"
                name="socialMediaUrl"
                error={Boolean(formik.errors.socialMediaUrl)}
                onChange={formik.handleChange}
                value={formik.values.socialMediaUrl}
                onBlur={formik.handleBlur}
                placeholder="Social Media Url"
              />
              {formik.errors.socialMediaUrl && formik.touched.socialMediaUrl && (
                <div className={"ui pointing red basic label"}>
                  {formik.errors.socialMediaUrl}
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
