import React from 'react';

export default (text, link) => (
  <a href={link === null ? "#" : link} target="_blank" rel="noopener noreferrer">
    {text === null ? "" : text}
  </a>
);