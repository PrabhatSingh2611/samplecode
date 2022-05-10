import { sub } from 'date-fns';
import { role } from '_mock/role';
import { email } from '_mock/email';
import { boolean } from '_mock/boolean';
import { company } from '_mock/company';
import { phoneNumber } from '_mock/phoneNumber';
import { fullAddress, country } from '_mock/address';
import { firstName, lastName, fullName } from '_mock/name';
import { title, sentence, description } from '_mock/text';
import { price, rating, age, percent } from '_mock/number';

const _mock = {
    id: (index: number) => `e99f09a7-dd88-49d5-b1c8-1daf80c2d7b${index + 1}`,
    email: (index: number) => email[index],
    phoneNumber: (index: number) => phoneNumber[index],
    time: (index: number) => sub(new Date(), { days: index, hours: index }),
    boolean: (index: number) => boolean[index],
    role: (index: number) => role[index],
    company: (index: number) => company[index],
    address: {
        fullAddress: (index: number) => fullAddress[index],
        country: (index: number) => country[index],
    },
    name: {
        firstName: (index: number) => firstName[index],
        lastName: (index: number) => lastName[index],
        fullName: (index: number) => fullName[index],
    },
    text: {
        title: (index: number) => title[index],
        sentence: (index: number) => sentence[index],
        description: (index: number) => description[index],
    },
    number: {
        percent: (index: number) => percent[index],
        rating: (index: number) => rating[index],
        age: (index: number) => age[index],
        price: (index: number) => price[index],
    },
    image: {
        cover: (index: number) =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/covers/cover_${index + 1}.jpg`,
        feed: (index: number) =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/feeds/feed_${index + 1}.jpg`,
        product: (index: number) =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/products/product_${
                index + 1
            }.jpg`,
        avatar: (index: number) =>
            `https://minimal-assets-api-dev.vercel.app/assets/images/avatars/avatar_${
                index + 1
            }.jpg`,
    },
};

export default _mock;
