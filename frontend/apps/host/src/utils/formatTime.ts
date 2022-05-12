import { format, formatDistanceToNow } from 'date-fns';

type TDate = Date | string | number;

// TODO: don't remove comment untill usage (VZ)
// ts-unused-exports:disable-next-line
export function fDate(date: TDate): string {
    return format(new Date(date), 'dd MMMM yyyy');
}

// TODO: don't remove comment untill usage (VZ)
// ts-unused-exports:disable-next-line
export function fDateTime(date: TDate): string {
    return format(new Date(date), 'dd MMM yyyy p');
}

export function fToNow(date: TDate): string {
    return formatDistanceToNow(new Date(date), {
        addSuffix: true,
    });
}
