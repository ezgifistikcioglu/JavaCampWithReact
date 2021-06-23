import React from 'react'
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import PropTypes from 'prop-types';
import Divider from '@material-ui/core/Divider';
import { makeStyles } from '@material-ui/core/styles';
import { useHistory } from "react-router-dom";

const useStyles = makeStyles({
    root: {
        background: (props) =>
            props.color === 'red'
                ? 'linear-gradient(45deg, #F34269 30%, #FF8E53 90%)'
                : 'linear-gradient(45deg, #8DEEA2 30%, #CBA0FB 90%)',
        borderRadius: 5,
        boxShadow: (props) =>
            props.color === 'red'
                ? '0 3px 5px 2px rgba(255, 105, 135, .3)'
                : '0 3px 5px 2px rgba(33, 203, 243, .3)',
        color: 'white',
        height: 42,
        padding: '0 30px',
        marginTop: "5px"
    },
});

function MyButton(props) {
    const { color, ...other } = props;
    const classes = useStyles(props);
    return <Button aria-controls="simple-menu" aria-haspopup="true" className={classes.root} {...other} />;
}

MyButton.propTypes = {
    color: PropTypes.oneOf(['blue', 'red']).isRequired,
};

export default function SignOut({ signIn }) {
    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };
    const history = useHistory();
    const handleRouteSeeker = () => {
        history.push("/jobSeekerRegister");
    }
    const handleRouteEmployee = () => {
        history.push("/employeeRegister");
    }
    const handleRouteEmployer = () => {
        history.push("/employerRegister");
    }
    const handleRouteLogin = () => {
        history.push("/login");
    }

    return (
        <div>
            <MenuItem>
                <MyButton color="blue" onClick={handleRouteLogin} >Sign In</MyButton>
                <MyButton color="red" onClick={handleClick} style={{ marginLeft: "1em" }}>Register</MyButton>
                <Menu
                    id="simple-menu"
                    anchorEl={anchorEl}
                    keepMounted
                    open={Boolean(anchorEl)}
                    onClose={handleClose}
                    PaperProps={{
                        style: {
                            transform: 'translateX(-37%) translateY(30%)',
                        }
                    }}
                    MenuListProps={{
                        style: {
                            padding: 7,
                            color: "#C7FFC5",
                            textAlign: "center",
                            fontWeight: "bold",
                            backgroundColor: "black",
                            fontStyle: "oblique"
                        },
                    }}
                >
                    <MenuItem onClick={handleRouteSeeker}>Job Seeker Register</MenuItem>
                    <Divider />
                    <MenuItem onClick={handleRouteEmployer}>Employer Register</MenuItem>
                    <Divider light />
                    <MenuItem onClick={handleRouteEmployee}>Employee Register</MenuItem>
                </Menu>
            </MenuItem>
        </div>
    )
}
