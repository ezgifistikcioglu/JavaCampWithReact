import React from "react";
import { Formik } from "formik";
import { Link } from "react-router-dom";
import * as Yup from "yup";
import { Button, Form, Header, Image } from "semantic-ui-react";

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';

import HrmsTextInput from "../../utilities/customFormControls/HrmsTextInput";

import { withRouter } from "react-router-dom"

const useStyles = makeStyles({
    root: {
        background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        borderRadius: 5,
        boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
        color: 'white',
        height: 42,
        padding: '0 30px',
        marginTop: "11px"
    },
})
const initialValues = { email: "", password: "" }

const LoginPage = (props) => (
    <Formik
        initialValues={initialValues}
        onSubmit={(values, { setSubmitting }) => {
            setTimeout(() => {
                // this redirects to /users, make sure you get the import withRouter (check the last export line)
                props.history.push("/");

            }, 500);
        }}

        validationSchema={Yup.object().shape({
            email: Yup.string()
                .email()
                .required("Required"),
            password: Yup.string()
                .required("No password provided.")
                .min(4, "Password is too short - should be 8 chars minimum.")
                .matches(/(?=.*[0-9])/, "Password must contain a number.")
        })

        }
    >
        {props => {
            const {
                values,
                touched,
                errors,
                isSubmitting,
                handleChange,
                handleBlur,
                handleSubmit
            } = props

            return (
                <div>
                    <Header as="h2" color="orange" textAlign="center">
                        <Image src="https://d1myhw8pp24x4f.cloudfront.net/software_logo/1495099147_logo-hrms_mid.png" />
                        Create Account - Sign-in
                    </Header>
                    <Form className="ui form" onSubmit={handleSubmit}>
                        <label><b>Email Address</b></label>
                        <HrmsTextInput
                            type="text"
                            value={values.email}
                            name="email"
                            onChange={handleChange}
                            onBlur={handleBlur} placeholder="Enter your email" className={errors.email && touched.email && "error"}

                        />
                        <label><b>Password</b></label>
                        <HrmsTextInput
                            type="password" value={values.password} name="password" onChange={handleChange} onBlur={handleBlur} placeholder="Enter your password" className={errors.password && touched.password && "error"}
                        />
                        <Button animated
                            content="Login"
                            labelPosition="right"
                            icon="login"
                            color="orange"
                            type="submit"
                            style={{ marginLeft: "20px" }} disabled={isSubmitting}/>
                        <Grid container>
                            <Grid item>
                                <Link to={"/jobSeekerRegister"} href="signup" variant="body2">
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>

                    </Form>
                </div>
            )
        }}
    </Formik>
)

// changes
export default withRouter(LoginPage)