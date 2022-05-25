enum HostEvent {
    NAVIGATE = 'host:navigate',
}

export const HOST_APP_CHANNEL = {
    NAVIGATE: {
        EVENT: HostEvent.NAVIGATE,
        SCHEMA: {
            type: 'object',
            properties: {
                pathname: {
                    type: 'string',
                },
                search: {
                    type: 'string',
                },
            },
            required: ['pathname'],
        },
    },
};

enum PeopleEvent {
    NAVIGATE = 'people:navigate',
}

export const PEOPLE_APP_CHANNEL = {
    NAVIGATE: {
        EVENT: PeopleEvent.NAVIGATE,
        SCHEMA: {
            type: 'object',
            properties: {
                pathname: {
                    type: 'string',
                },
                search: { type: 'string' },
            },
            required: ['pathname'],
        },
    },
};
