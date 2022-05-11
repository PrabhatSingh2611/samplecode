import React from "react";

export function ExternalLink({ url }) {
  return (
    <a href={url} target="_blank">
      {url}
    </a>
  );
}

export function TeamAll() {
  return (
    <b
      style={{
        backgroundColor: "magenta",
        padding: "4px 8px",
        color: "white",
        borderRadius: "5px",
      }}
    >
      All
    </b>
  );
}

export function TeamLelekaMore() {
  return (
    <b
      style={{
        backgroundColor: "darkcyan",
        padding: "4px 8px",
        color: "white",
        borderRadius: "5px",
      }}
    >
      Leleka More
    </b>
  );
}

export function TeamAvengers() {
  return (
    <b
      style={{
        backgroundColor: "coral",
        padding: "4px 8px",
        color: "white",
        borderRadius: "5px",
      }}
    >
      Avengers
    </b>
  );
}

export function TeamAchivers() {
  return (
    <b
      style={{
        backgroundColor: "blueviolet",
        padding: "4px 8px",
        color: "white",
        borderRadius: "5px",
      }}
    >
      Achivers
    </b>
  );
}
