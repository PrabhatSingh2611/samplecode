import _mock from './_mock';
import { randomStringInArray } from './funcs';

export const _carouselsExample = [...Array(5)].map((_, index) => ({
    id: _mock.id(index),
    title: _mock.text.title(index),
    image: _mock.image.feed(index),
    description: _mock.text.description(index),
}));

export const _contacts = [...Array(20)].map((_, index) => ({
    id: _mock.id(index),
    name: _mock.name.fullName(index),
    username: _mock.name.fullName(index),
    avatar: _mock.image.avatar(index),
    address: _mock.address.fullAddress(index),
    phone: _mock.phoneNumber(index),
    email: _mock.email(index),
    lastActivity: _mock.time(index),
    status: randomStringInArray(['online', 'offline', 'away', 'busy']),
    position: _mock.role(index),
}));

export const _notifications = [...Array(5)].map((_, index) => ({
    id: _mock.id(index),
    title: [
        'Your order is placed',
        'Sylvan King',
        'You have new message',
        'You have new mail',
        'Delivery processing',
    ][index],
    description: [
        'waiting for shipping',
        'answered to your comment on the Minimal',
        '5 unread messages',
        'sent from Guido Padberg',
        'Your order is being shipped',
    ][index],
    avatar: [null, _mock.image.avatar(2), null, null, null][index],
    type: ['order_placed', 'friend_interactive', 'chat_message', 'mail', 'order_shipped'][index],
    createdAt: _mock.time(index),
    isUnRead: [true, true, false, false, false][index],
}));
