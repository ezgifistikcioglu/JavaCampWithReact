import React from 'react';

export default (text, link) => (
  <a href={link} target="_blank" rel="noopener noreferrer">
    {text}
  </a>
);