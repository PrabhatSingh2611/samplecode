export function randomNumberRange(min: number, max: number) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

export function randomNumberInArray(array: number[]) {
    return array[Math.floor(Math.random() * array.length)];
}

export function randomStringInArray(array: string[]) {
    return array[Math.floor(Math.random() * array.length)];
}
