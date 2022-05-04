/// <reference types="react" />

interface MountProps {
    element: Element;
    inIsolation?: boolean;
    initialEntry?: string;
}

declare module 'people/PeopleApp' {
    export const mount: (props: MountProps) => () => void;
}
