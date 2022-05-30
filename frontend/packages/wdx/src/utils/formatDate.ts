import { format, getTime, formatDistanceToNow } from 'date-fns';

export function formatDate(date: Date | string | number) {
  return format(new Date(date), 'dd MMMM yyyy');
}

export function formatDateTime(date: Date | string | number) {
  return format(new Date(date), 'dd MMM yyyy p');
}

export function formatTimestamp(date: Date | string | number) {
  return getTime(new Date(date));
}

export function formatDateTimeSuffix(date: Date | string | number) {
  return format(new Date(date), 'dd/MM/yyyy hh:mm p');
}

export function formatDateToNow(date: Date | string | number) {
  return formatDistanceToNow(new Date(date), {
    addSuffix: true,
  });
}

export function formatDateByTemplate(
  date: Date | string | number,
  template: string
): string {
  return format(new Date(date), template);
}

export function deleteUtcFromDate(date: string): string {
  return date.replace('[UTC]', '');
}
