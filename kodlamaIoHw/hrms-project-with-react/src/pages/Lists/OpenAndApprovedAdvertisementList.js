import React, { useState, useEffect } from 'react';
import { Button, Icon } from 'semantic-ui-react';
import { NavLink } from "react-router-dom";
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import AdvertisementService from '../../services/advertisementService';

const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
        fontWeight: "bold",
    },
    body: {
        fontSize: 12,
        fontWeight: "bold",
        textAlign: "center",
        fontFamily: "arial",
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

const useStyles = makeStyles({
    table: {
        WebkitBorderRadius : 4,
        textAlign : "center",
        marginTop: 1,
        minWidth: 300,        
    },
});

export default function OpenAndApprovedAdvertisementList() {
    const classes = useStyles();

    const [advertisements, setAdvertisements] = useState([]);

    useEffect(() => {
        let advertisementService = new AdvertisementService();
        advertisementService.getAllOpenAndApprovedAdvertisementList().then(result => setAdvertisements(result.data.data))
    }, []);

    return (
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
                <TableHead>
                    <TableRow>
                        <StyledTableCell align="right">Job Description</StyledTableCell>
                        <StyledTableCell align="right">Min Salary</StyledTableCell>
                        <StyledTableCell align="right">Max Salary</StyledTableCell>
                        <StyledTableCell align="right">Application Deadline</StyledTableCell>
                        <StyledTableCell align="right">Open Position</StyledTableCell>
                        <StyledTableCell align="right">Work Type</StyledTableCell>
                        <StyledTableCell align="right">Work Time</StyledTableCell>
                        <StyledTableCell align="right">City Name</StyledTableCell>
                        <StyledTableCell align="right">Activation Status</StyledTableCell>
                        <StyledTableCell align="right">Is Approved</StyledTableCell>
                        <StyledTableCell align="right">Detail</StyledTableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {
                        advertisements.map(advertisement => (
                            <StyledTableRow key={advertisement.id}>
                                <StyledTableCell>{advertisement.jobDescription}</StyledTableCell>
                                <StyledTableCell>{advertisement.minSalary}</StyledTableCell>
                                <StyledTableCell>{advertisement.maxSalary}</StyledTableCell>
                                <StyledTableCell>{advertisement.applicationDeadline}</StyledTableCell>
                                <StyledTableCell>{advertisement.numberOfOpenPosition}</StyledTableCell>
                                <StyledTableCell>{advertisement.typeOfWorkFeature.workTypeName}</StyledTableCell>
                                <StyledTableCell>{advertisement.workTimeFeature.workTimeName}</StyledTableCell>
                                <StyledTableCell>{advertisement.city.cityName}</StyledTableCell>
                                <StyledTableCell>
                                    {advertisement.advertisementOpen === true ? <Icon name="large circle" color="green" /> : <Icon name="large circle outline" color="red" />}
                                </StyledTableCell>
                                <StyledTableCell >
                                    {advertisement.approved === true ? <Icon name="big chevron down" color="green" /> : <Icon name="large x icon" color="red" />}
                                </StyledTableCell>
                                <StyledTableCell >
                                    <Button animated as={NavLink} to={`/advertisementDetails/${advertisement.id}`} color="orange">
                                        <Button.Content visible>Show Detail</Button.Content>
                                        <Button.Content hidden>
                                            <Icon name="arrow right" />
                                        </Button.Content>
                                    </Button>
                                </StyledTableCell>
                            </StyledTableRow>
                        ))
                    }
                </TableBody>
            </Table>
        </TableContainer>
    );
}
