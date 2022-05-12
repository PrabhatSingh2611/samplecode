export function randomNumberRange(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// TODO: don't remove comment untill usage (VZ)
// ts-unused-exports:disable-next-line
export function randomNumberInArray(array: number[]): number {
    return array[Math.floor(Math.random() * array.length)];
}

export function randomStringInArray(array: string[]): string {
    return array[Math.floor(Math.random() * array.length)];
}
