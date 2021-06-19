import React from 'react'
import Button from '@material-ui/core/Button';
import { Menu } from 'semantic-ui-react'
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        background: (props) =>
            props.color === 'red'
                ? 'linear-gradient(45deg, #F34269 30%, #FF8E53 90%)'
                : 'linear-gradient(45deg, #8DEEA2 30%, #CBA0FB 90%)',
        border: 0,
        borderRadius: 3,
        boxShadow: (props) =>
            props.color === 'red'
                ? '0 3px 5px 2px rgba(255, 105, 135, .3)'
                : '0 3px 5px 2px rgba(33, 203, 243, .3)',
        color: 'white',
        height: 42,
        padding: '0 30px',
        marginTop: "0.5px"
    },
});

function MyButton(props) {
    const { color, ...other } = props;
    const classes = useStyles(props);
    return <Button className={classes.root} {...other} />;
}

MyButton.propTypes = {
    color: PropTypes.oneOf(['blue', 'red']).isRequired,
};

export default function SignOut({ signIn }) {

    return (
        <div>
            <Menu.Item>
                <React.Fragment>
                    <MyButton color="blue" onClick={signIn} >Sign In</MyButton>
                    <MyButton color="red" style={{ marginLeft: "1em" }}>Sign Out</MyButton>
                </React.Fragment>
            </Menu.Item>
        </div>
    )
}
