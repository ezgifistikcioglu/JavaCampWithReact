import React from 'react';
import { Rating, Grid, GridColumn } from "semantic-ui-react";

export default rating => (
  <Grid>
    <GridColumn style={{ padding: "0.4em" }} >
    <Rating
      style={{ float: "right" }}
      defaultRating={rating}
      maxRating={5}
      icon="star"
      size='small'
      disabled
    /></GridColumn>
  </Grid>

);