import { format, formatDistanceToNow } from 'date-fns';

type TDate = Date | string | number;

export function fDate(date: TDate) {
    return format(new Date(date), 'dd MMMM yyyy');
}

export function fDateTime(date: TDate) {
    return format(new Date(date), 'dd MMM yyyy p');
}

export function fToNow(date: TDate) {
    return formatDistanceToNow(new Date(date), {
        addSuffix: true,
    });
}
