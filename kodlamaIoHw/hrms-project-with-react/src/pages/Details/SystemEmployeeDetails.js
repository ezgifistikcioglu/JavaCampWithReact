import React, { useEffect, useState } from 'react'
import { useParams } from "react-router";
import { Icon, Grid, Card,Button } from "semantic-ui-react";
import { NavLink } from "react-router-dom";
import { withStyles, makeStyles } from '@material-ui/core/styles';
import CustomizedDialogs from "../../utilities/cvs/components/dialog";
import SystemEmployeeService from '../../services/systemEmployeeService';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const StyledTableCell = withStyles((theme) => ({
    head: {
        fontSize: 15,
        backgroundColor: "#ff6f00",
        color: theme.palette.common.white,
        fontWeight: "bold",
    },
    body: {
        fontSize: 14,
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
        marginTop: 3,
        minWidth: 350,        
    },
});

export default function SystemEmployeeDetails() {
    let { id } = useParams();
    const classes = useStyles();

    const [systemEmployees, setSystemEmployees] = useState([]);

    useEffect(() => {
      let  systemEmployeeService = new SystemEmployeeService();
      systemEmployeeService.getById(id).then(result=>setSystemEmployees(result.data.data));
    }, [id]);

    return (
        <TableContainer component={Paper}>

            <Card fluid color={"orange"}>
                <Card.Content header="System Employee Details" />
                <Card.Content>{systemEmployees.createdAt}</Card.Content>
            </Card>
            <Grid centered stackable>
                <Grid.Row>
                    <Grid.Column width={10}>
                        <Table  className={classes.table} aria-label="customized table" >
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell>Email</StyledTableCell>
                                    <StyledTableCell>First Name</StyledTableCell>
                                    <StyledTableCell>Last Name</StyledTableCell>
                                    <StyledTableCell>Edit</StyledTableCell>
                                </TableRow>
                            </TableHead>

                            <TableBody>
                            <StyledTableRow key={systemEmployees.userId}>
                                    <StyledTableCell>{systemEmployees.email}</StyledTableCell>
                                    <StyledTableCell>{systemEmployees.firstName}</StyledTableCell>
                                    <StyledTableCell>{systemEmployees.lastName}</StyledTableCell>
                                    <StyledTableCell >
                                    <div style={{ justifyContent: "flex-end", display: "flex" }}>
                                            <CustomizedDialogs color="blue" icon="edit">
                                               
                                            </CustomizedDialogs>
                                            <hr/>
                                            <Button size='mini' icon="delete" color="red" onClick={{}} compact="true" />
                                        </div>
                                    </StyledTableCell>
                                    </StyledTableRow>
                            </TableBody>
                        </Table>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </TableContainer>
    );
}
