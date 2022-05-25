import { useHistory, useLocation } from 'react-router-dom';
import { useCallback, useEffect, useMemo } from 'react';

export const useAddQueryParamsToUrl = (params: {
  [key: string]: string | number;
}): void => {
  const history = useHistory();

  return useEffect(() => {
    const searchParams = new URLSearchParams(history.location.search);

    Object.keys(params).forEach((key: string) => {
      searchParams.set(key, params[key].toString());
    });
    history.replace(`${history.location.pathname}?${searchParams}`);
  }, [params, history.location.search]);
};

type TGetUrlSearchParamsResult = { [key: string]: string | undefined };

export const useGetUrlSearchParams = (
  paramsName: string[]
): TGetUrlSearchParamsResult => {
  const location = useLocation();

  return useMemo(() => {
    const result: TGetUrlSearchParamsResult = {};

    const searchParams = new URLSearchParams(location.search);
    paramsName.forEach((key: string) => {
      result[key] = searchParams.get(key) || undefined;
    });

    return result;
  }, [location.search]);
};

export const useUpdateSearchUrlParam = (): (<T>(
  paramName: T,
  value: any
) => void) => {
  const history = useHistory();
  return useCallback(
    (paramName, value) => {
      const searchParams = new URLSearchParams(history.location.search);
      searchParams.set(`${paramName}`, value.toString());
      history.replace(`${history.location.pathname}?${searchParams}`);
    },
    [history.location.search]
  );
};
