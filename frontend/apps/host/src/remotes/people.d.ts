/// <reference types="react" />

interface IMountProps {
    element: Element | null;
    inIsolation?: boolean;
    initialEntry?: string;
}

declare module 'people/PeopleApp' {
    export const mount: (props: IMountProps) => () => void;
}
