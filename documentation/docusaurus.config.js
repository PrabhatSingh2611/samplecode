// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require("prism-react-renderer/themes/github");
const darkCodeTheme = require("prism-react-renderer/themes/dracula");

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: "Audra",
  tagline:
    "One source of truth, auto-deploy, MS Teams Notifications, easily to integrate in dev flow, everyone can update FE, BE, QA, DevOPs, PO, PM, Global Search, Blog to keep retrospectives and meeting notes.",
  url: "https://your-docusaurus-test-site.com",
  baseUrl: "/",
  onBrokenLinks: "throw",
  onBrokenMarkdownLinks: "warn",
  favicon: "img/favicon.ico",
  organizationName: "windmill", // Usually your GitHub org/user name.
  projectName: "audra-platform", // Usually your repo name.

  presets: [
    [
      "classic",
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve("./sidebars.js"),
          // Please change this to your repo.
          editUrl:
            "https://bitbucket.org/windmillgmbh/audra-platform/src/master/documentation",
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          editUrl:
            "https://bitbucket.org/windmillgmbh/audra-platform/src/master/documentation",
        },
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: "Audra",
        logo: {
          alt: "Audra Logo",
          src: "img/logo.svg",
        },
        items: [
          {
            type: "doc",
            docId: "intro",
            position: "left",
            label: "Tutorial",
          },
          {
            type: "doc",
            docId: "frontend/intro",
            position: "left",
            label: "Frontend",
          },
          {
            type: "doc",
            docId: "backend/intro",
            position: "left",
            label: "Backend",
          },
          {
            type: "doc",
            docId: "api/schema-conventions",
            position: "left",
            label: "API",
          },
          {
            type: "doc",
            docId: "project/intro",
            position: "left",
            label: "Project",
          },
          { to: "/blog", label: "Blog", position: "left" },
          {
            href: "https://bitbucket.org/windmillgmbh/audra-platform",
            label: "BitBucket",
            position: "right",
          },
        ],
      },
      footer: {
        style: "dark",
        links: [
          {
            title: "Docs",
            items: [
              {
                label: "Tutorial",
                to: "/docs/intro",
              },
            ],
          },
          {
            title: "Community",
            items: [
              {
                label: "Stack Overflow",
                href: "https://stackoverflow.com/questions/tagged/docusaurus",
              },
              {
                label: "Discord",
                href: "https://discordapp.com/invite/docusaurus",
              },
              {
                label: "Twitter",
                href: "https://twitter.com/docusaurus",
              },
            ],
          },
          {
            title: "More",
            items: [
              {
                label: "Blog",
                to: "/blog",
              },
              {
                label: "BitBucket",
                href: "https://bitbucket.org/windmillgmbh/audra-platform",
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Windmill, Inc. Built with Docusaurus.`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
    }),
};

module.exports = config;
