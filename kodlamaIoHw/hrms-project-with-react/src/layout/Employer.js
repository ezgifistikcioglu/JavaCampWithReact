import React from 'react'
import { makeStyles } from '@material-ui/core/styles';
import { useHistory } from "react-router-dom";
import Button from '@material-ui/core/Button';

const useStyles = makeStyles({
    root: {
        background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        border: 0,
        borderRadius: 3,
        boxShadow: '0 3px 5px 2px rgba(255, 105, 135, .3)',
        color: 'white',
        height: 48,
        padding: '0 30px',
        marginTop: "15px"
    },
});

export default function Employer({advertisement}) {
    const classes = useStyles();
    const history = useHistory();
    const handleRoute = () =>{ 
        history.push("/advertisements");
      }
    return (
        <Button onClick={handleRoute} className={classes.root} variant="contained" fullWidth type="submit">
            Add Advertisement
        </Button>
    )
}
