import * as faker from 'faker';

export function getRandomUUID(): string {
  return faker.datatype.uuid().toString();
}

export function getRandomCity(): string {
  return faker.address.city();
}

export function getRandomCountry(): string {
  return faker.address.country();
}
