import React from 'react';

export const Input = () => {
    return (
        <div>
            <label htmlFor="name">Name</label>
            <input
                type="text"
                id="name"
                name="name"
                required
                minLength={4}
                maxLength={8}
                size={10}
            />
        </div>
    );
};
