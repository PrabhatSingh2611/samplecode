/// <reference types="react" />

interface IMountProps {
    element: Element | null;
    inIsolation?: boolean;
    initialEntry?: string;
}

declare module 'assets/AssetsApp' {
    export const mount: (props: IMountProps) => () => void;
}
