import React, { useEffect, useState } from 'react'
import { useParams } from "react-router";
import { Button, Input, Card, Form } from "semantic-ui-react";
import { useFormik } from 'formik';
import * as Yup from "yup";
import swal from 'sweetalert';
import ProgrammingSkillService from '../../../../services/programmingSkillService';


export default function AddProgrammingSkillForCv() {
    let { id } = useParams();

    let programmingSkillService = new ProgrammingSkillService();

    const [programmingSkills, setProgrammingSkills] = useState({});

    
  useEffect(() => {
    programmingSkillService.getByCvId(id).then((result) => setProgrammingSkills(result.data.data));
}, [id]);

    const validationSchema = Yup.object({
        programmingName: Yup.string().max(250, 'Must be 250 characters or less').required("Please write a programming skill name"),
        programmingSkillLevel: Yup.number().required("Please enter the number of programming skill level for your cv."),
        createdAt: Yup.date().default(() => new Date()),
    });

    const formik = useFormik({
        initialValues: {
            programmingName: "",
            programmingSkillLevel: "",
            createdAt: new Date(),
            cvId: "",
        },
        validationSchema: validationSchema,
        onSubmit: (values) => {
            console.log("values: " + values)
            values.cvId = parseInt(id);

            programmingSkillService.addProgrammingSkills(values).then((result) => console.log(result)).then(swal({
                title: "Succeed!",
                text: "Programming skill is added!",
                icon: "success",
                button: "Ok"
            }).then(function () { window.location.reload() }));
        },
    });

    return (
        <div>
            <Card color="orange" fluid>
                <Card.Content header='Add Programming Skill' />
                <Card.Content>
                    <Form color="orange" onSubmit={formik.handleSubmit}>
                        <Form.Field style={{ marginBottom: "1em" }}>
                            <Input
                                style={{ width: "100%" }}
                                error={Boolean(formik.errors.programmingName).toString()}
                                onChange={formik.handleChange}
                                value={formik.values.programmingName}
                                onBlur={formik.handleBlur}
                                name="programmingName"
                                placeholder="Programming Name"
                            />
                            {formik.errors.programmingName && formik.touched.programmingName && (
                                <div className={"ui pointing red basic label"}>
                                    {formik.errors.programmingName}
                                </div>
                            )}
                        </Form.Field>
                        <Form.Field>
                            <Input
                                style={{ width: "100%" }}
                                id="programmingSkillLevel"
                                name="programmingSkillLevel"
                                error={Boolean(formik.errors.programmingSkillLevel)}
                                onChange={formik.handleChange}
                                value={formik.values.programmingSkillLevel}
                                onBlur={formik.handleBlur}
                                type="number"
                                placeholder="Programming Skill Level"
                            />
                            {formik.errors.programmingSkillLevel && formik.touched.programmingSkillLevel && (
                                <div className={"ui pointing red basic label"}>
                                    {formik.errors.programmingSkillLevel}
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
