import { sub } from 'date-fns';

import { fullAddress, country } from '_mock/address';
import { boolean } from '_mock/boolean';
import { company } from '_mock/company';
import { email } from '_mock/email';
import { firstName, lastName, fullName } from '_mock/name';
import { price, rating, age, percent } from '_mock/number';
import { phoneNumber } from '_mock/phoneNumber';
import { role } from '_mock/role';
import { title, sentence, description } from '_mock/text';

const _mock = {
    id: (index: number): string => `e99f09a7-dd88-49d5-b1c8-1daf80c2d7b${index + 1}`,
    email: (index: number): string => email[index],
    phoneNumber: (index: number): string => phoneNumber[index],
    time: (index: number): Date => sub(new Date(), { days: index, hours: index }),
    boolean: (index: number): boolean => boolean[index],
    role: (index: number): string => role[index],
    company: (index: number): string => company[index],
    address: {
        fullAddress: (index: number): string => fullAddress[index],
        country: (index: number): string => country[index],
    },
    name: {
        firstName: (index: number): string => firstName[index],
        lastName: (index: number): string => lastName[index],
        fullName: (index: number): string => fullName[index],
    },
    text: {
        title: (index: number): string => title[index],
        sentence: (index: number): string => sentence[index],
        description: (index: number): string => description[index],
    },
    number: {
        percent: (index: number): number => percent[index],
        rating: (index: number): number => rating[index],
        age: (index: number): number => age[index],
        price: (index: number): number => price[index],
    },
    image: {
        cover: (index: number): string =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/covers/cover_${index + 1}.jpg`,
        feed: (index: number): string =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/feeds/feed_${index + 1}.jpg`,
        product: (index: number): string =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/products/product_${
                index + 1
            }.jpg`,
        avatar: (index: number): string =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/avatars/avatar_${
                index + 1
            }.jpg`,
    },
};

export default _mock;
