// eslint-disable-next-line @typescript-eslint/no-explicit-any
type TObject = Record<string, any>;

export const castToInt = (
  value: number | null | undefined,
  fallback = 0
): number => {
  return value || fallback;
};

export const castToString = (
  value: string | null | undefined,
  fallback = ''
): string => {
  return value || fallback;
};

export const castToArray = <T>(
  value: T[] | null | undefined,
  fallback = []
): T[] => {
  return value || fallback;
};

export const castToObject = (
  value: TObject | null | undefined,
  fallback = {}
): TObject => {
  return value || fallback;
};

export const castToBoolean = (
  value: boolean | null | undefined,
  fallback = false
): boolean => {
  return value || fallback;
};
